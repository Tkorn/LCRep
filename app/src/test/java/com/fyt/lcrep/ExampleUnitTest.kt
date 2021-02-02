package com.fyt.lcrep

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    /**
     * 用两个栈实现一个队列。队列的声明如下，
     * 请实现它的两个函数 appendTail 和 deleteHead ，
     * 分别完成 在队列尾部插入整数 和 在队列头部删除整数 的功能。
     * (若队列中没有元素，deleteHead 操作返回 -1 )
     *
    输入：
    ["CQueue","appendTail","deleteHead","deleteHead"]
    [[],[3],[],[]]
    输出：[null,null,3,-1]
     */
    internal class CQueue {
        private val stackAppend = Stack<Int>()
        private val stackDelete = Stack<Int>()

        fun appendTail(value: Int) {
            if (stackAppend.empty()){
                stackAppend.push(value)
            }else{
                stackAppend.forEach {
                    stackDelete.push(it)
                }
                stackAppend.clear()
                stackAppend.push(value)
                stackDelete.forEach {
                    stackAppend.push(it)
                }
                stackDelete.clear()
            }
        }
        fun deleteHead(): Int {
            return if (stackAppend.empty()){
                -1
            }else{
                stackAppend.pop()
            }
        }
    }
}