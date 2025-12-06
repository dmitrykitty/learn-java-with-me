package com.dnikitin.multithreading.threadsafetyobject;

public class SafetyObjectDemo {
    /**
     * How to make thread safe method?
     * <li>
     *     Do not create objects common for different threads
     * </li>
     * <li>
     *     Use classes without attributes (only methods and static fileds)
     * </li>
     * <li>
     *     Immutable objects(for example String). After changing our object creates new one
     * </li>
     * <li>
     *     Create only read only methods (for example getters). <b>NOTE:</b> getter can return mutable object by its reference
     * </li>
     * <li>
     *     All methods changing our object make synchronized or use synchronized collections
     * </li>
     */
    static void main() {

    }
}
