package com.xjk.base.app

import android.app.Application
import android.content.Context
import androidx.annotation.CallSuper
import com.blankj.utilcode.util.CrashUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.tencent.mmkv.MMKV
import com.xjk.base.BuildConfig
import com.xjk.base.integration.ActivityLifecycle
import com.xjk.base.integration.AppManager
import com.xjk.base.integration.ManifestParser
import com.xjk.base.integration.config.AppLifecycle
import com.xjk.base.integration.config.ConfigModule
import com.xjk.base.integration.config.IRepositoryManager
import com.xjk.base.kodein.ClientModule
import com.xjk.base.kodein.GlobeConfigModule
import com.xjk.base.kodein.appModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidCoreModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * description : This person is too lazy to leave anything.
 *
 * Create by LianXin on 2022/8/13 10:23
 */
open class BaseApplication : Application(), KodeinAware {

    companion object {
        /** 提供一个全局的Context对象 */
        lateinit var instance: BaseApplication
    }

    /** App生命周期代理列表 */
    private val mAppLifecycleList = ArrayList<AppLifecycle>()
    private val mActivityLifecycleCallbacksList = ArrayList<ActivityLifecycleCallbacks>()

    /** 模块化配置的列表 */
    private var configModules: List<ConfigModule>? = null

    private val appManager: AppManager by instance()
    private val repositoryManager: IRepositoryManager by instance()

    /** 初始化全局kodein */
    override val kodein: Kodein = Kodein {
        bind<Context>() with singleton { this@BaseApplication }
        import(androidCoreModule(this@BaseApplication))
        configModules = getModuleConfig()
        configModules?.let {
            import(getGlobeConfigModule(it).globeConfigModule)
        }
        import(appModule)
        import(ClientModule.clientModule)
        initKodein(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        injectConfigModule(configModules)
        mAppLifecycleList.forEach {
            it.onCreate(this)
        }
        registerActivityLifecycleCallbacks(ActivityLifecycle(appManager))
        mActivityLifecycleCallbacksList.forEach {
            registerActivityLifecycleCallbacks(it)
        }
        // MMKV初始化
        MMKV.initialize(this)
    }

    /**
     * 在同意隐私协议之后需要调用此方法初始化三方的SDK
     */
    @CallSuper
    open fun initThirdSdk() {
        // BlackJ初始化
        Utils.init(this)
        CrashUtils.init()
        LogUtils.getConfig().apply {
            // log开关
            isLogSwitch = BuildConfig.DEBUG
            // 全局标签
            globalTag = "lainXin"
            // log栈
            stackDeep = 3
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        mAppLifecycleList.forEach {
            it.attachBaseContext(base)
        }
    }

    /** 程序终止的时候执行 */
    override fun onTerminate() {
        super.onTerminate()
        mAppLifecycleList.forEach {
            it.onTerminate(this)
        }
    }

    /** 获取模块的配置 */
    private fun getModuleConfig(): List<ConfigModule> = ManifestParser(this).parse()

    /**
     * 将app的全局配置信息封装进module(使用Dagger注入到需要配置信息的地方)
     * 需要在AndroidManifest中声明[ConfigModule]的实现类,和Glide的配置方式相似
     */
    private fun getGlobeConfigModule(modules: List<ConfigModule>): GlobeConfigModule {
        // 为了防止用户没有通过GlobeConfigModule配置baseurl,而导致报错,所以提前配置个默认baseurl
        val builder = GlobeConfigModule.builder()
            .baseUrl("https://api.github.com")
        for (module in modules) {
            module.applyOptions(this, builder)
        }
        return builder.build()
    }

    private fun injectConfigModule(modules: List<ConfigModule>?) {
        modules?.forEach {
            it.injectAppLifecycle(this, mAppLifecycleList)
            it.registerComponents(this, repositoryManager)
            it.injectActivityLifecycle(this, mActivityLifecycleCallbacksList)
        }
    }

    /** 初始化kodein */
    protected open fun initKodein(builder: Kodein.MainBuilder) {}

}