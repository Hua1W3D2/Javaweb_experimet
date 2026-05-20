package com.itheima.utils;

/***
 * ThreadLocal操作的工具类，用于操作当前员工id
 */
public class CurrentHolder {
    private static final ThreadLocal<Integer> CURRENT_LOCAL=new ThreadLocal<>();
    public static void setCurrentId(Integer empId){
        CURRENT_LOCAL.set(empId);
    }
    public static Integer getCurrentId(){
        return CURRENT_LOCAL.get();
    }
    public static void remove(){
        CURRENT_LOCAL.remove();
    }


}
