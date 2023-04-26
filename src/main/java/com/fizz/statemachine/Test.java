package com.fizz.statemachine;

import com.alibaba.cola.statemachine.Action;
import com.alibaba.cola.statemachine.Condition;
import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.builder.StateMachineBuilder;
import com.alibaba.cola.statemachine.builder.StateMachineBuilderFactory;
import com.fizz.statemachine.enums.EventEnum;
import com.fizz.statemachine.enums.OrderStatusEnum;

public class Test {

    public static void main(String[] args) {
        StateMachineBuilder<OrderStatusEnum, EventEnum, Object> builder = StateMachineBuilderFactory.create();
        builder.externalTransition().from(OrderStatusEnum.UN_PAY).to(OrderStatusEnum.PAID).on(EventEnum.PAY)
                .when(new Condition<Object>() {
            @Override
            public boolean isSatisfied(Object context) {
                return false;
            }
        }).perform(new Action<OrderStatusEnum, EventEnum, Object>() {
            @Override
            public void execute(OrderStatusEnum from, OrderStatusEnum to, EventEnum event, Object context) {
                System.out.println("do action");
            }
        });
        builder.externalTransition().from(OrderStatusEnum.PAID).to(OrderStatusEnum.FINISHED).on(EventEnum.RECEIVE);
        builder.externalTransition().from(OrderStatusEnum.PAID).to(OrderStatusEnum.CANCEL).on(EventEnum.REFUND);
        StateMachine<OrderStatusEnum, EventEnum, Object> stateMachine = builder.build("1");
        OrderStatusEnum orderStatusEnum = stateMachine.fireEvent(OrderStatusEnum.UN_PAY, EventEnum.PAY, new Object());
        System.out.println(stateMachine.fireEvent(OrderStatusEnum.UN_PAY, EventEnum.PAY, new Object()));
        System.out.println(stateMachine.fireEvent(OrderStatusEnum.PAID, EventEnum.RECEIVE, new Object()));
        System.out.println(stateMachine.fireEvent(OrderStatusEnum.PAID, EventEnum.REFUND, new Object()));
        System.out.println(stateMachine.generatePlantUML());
        stateMachine.showStateMachine();
    }
}
