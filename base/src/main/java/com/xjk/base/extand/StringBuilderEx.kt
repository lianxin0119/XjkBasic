package com.xjk.base.extand

import java.lang.StringBuilder

/**
 * description : This person is too lazy to leave anything.
 *
 * Create by LianXin on 2022/8/13 9:32
 */

/**
 * 操作符重载
 */
operator fun StringBuilder.plus(args: Any): StringBuilder = this.append(args)
operator fun StringBuilder.plus(args: String): StringBuilder = this.append(args)
operator fun StringBuilder.plus(args: StringBuffer): StringBuilder = this.append(args)
operator fun StringBuilder.plus(args: CharSequence): StringBuilder = this.append(args)
operator fun StringBuilder.plus(args: CharArray): StringBuilder = this.append(args)
operator fun StringBuilder.plus(args: Boolean): StringBuilder = this.append(args)
operator fun StringBuilder.plus(args: Char): StringBuilder = this.append(args)
operator fun StringBuilder.plus(args: Int): StringBuilder = this.append(args)
operator fun StringBuilder.plus(args: Long): StringBuilder = this.append(args)
operator fun StringBuilder.plus(args: Float): StringBuilder = this.append(args)
operator fun StringBuilder.plus(args: Double): StringBuilder = this.append(args)

operator fun StringBuffer.plus(args: Any): StringBuffer = this.append(args)
operator fun StringBuffer.plus(args: String): StringBuffer = this.append(args)
operator fun StringBuffer.plus(args: StringBuffer): StringBuffer = this.append(args)
operator fun StringBuffer.plus(args: CharSequence): StringBuffer = this.append(args)
operator fun StringBuffer.plus(args: CharArray): StringBuffer = this.append(args)
operator fun StringBuffer.plus(args: Boolean): StringBuffer = this.append(args)
operator fun StringBuffer.plus(args: Char): StringBuffer = this.append(args)
operator fun StringBuffer.plus(args: Int): StringBuffer = this.append(args)
operator fun StringBuffer.plus(args: Long): StringBuffer = this.append(args)
operator fun StringBuffer.plus(args: Float): StringBuffer = this.append(args)
operator fun StringBuffer.plus(args: Double): StringBuffer = this.append(args)



