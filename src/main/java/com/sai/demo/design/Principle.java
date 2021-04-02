package com.sai.demo.design;

import java.util.ArrayList;
import java.util.List;

public class Principle {

}

class Student{

}

class Teacher{

    public void command(GroupLeader groupLeader){
        List<Student> studentList = new ArrayList<>();

        for(int i =0;i<100;i++){
            studentList.add(new Student());
        }

        groupLeader.countStudent(studentList);
    }

}

class GroupLeader{

    public void countStudent(List<Student> studentList){
        //do count
    }
}



class Teacher2{

    public void command(GroupLeader2 groupLeader){
        groupLeader.countCommand();
    }

}

class GroupLeader2{
    List<Student> studentList = new ArrayList<>();
    public GroupLeader2(List<Student> studentList){
        this.studentList = studentList;
    }

    private void countStudent(){
        //do count studentList
    }

    public void countCommand(){
        this.countStudent();
    }
}

//中介者
//销售
class Sale{
    public void sell(){
        //访问库存
        Stock stock = new Stock();
        //访问采购
        Purchase purchase = new Purchase();
        //购买流程
    }

    //清仓处理
    public  void offSale(){
        Stock stock=new Stock();
        stock.clearStock();
    }
}
//库存管理
class Stock{
    public void incr(){
        //增加库存
    }
    public void decr(){
        //减少库存
    }
    public void getStock(){
        //获取库存
    }
    public void clearStock(){
        //清理库存
        Purchase purchase= new Purchase();
        Sale sale = new Sale();
        sale.offSale();
    }
}
//采购管理
class Purchase{
    public void buyComputer(int num){
        Stock stock = new Stock();
        Sale sale = new Sale();
        //购买逻辑
    }

}


//改进
abstract class AbstarctMediator{
    protected Purchase2 purchase;
//    protected Sale2 sale;
    protected Stock2 stock;

    //买电脑事件
    abstract void buy();

    //清仓
    abstract void clear();
}


class Mediator2 extends AbstarctMediator{
    @Override
    void buy() {
//        super.sale;
//        super.stock;
    }

    @Override
    void clear() {
//        super.stock.clear();
    }
}

abstract class AbstractColleague{
    protected AbstarctMediator mediator;
}

class Stock2 extends AbstractColleague{
    public void buy(){
        mediator.buy();
    }
}

class Purchase2 extends AbstractColleague{
    public void clear(){
        mediator.clear();
    }
}

//桥梁模式
