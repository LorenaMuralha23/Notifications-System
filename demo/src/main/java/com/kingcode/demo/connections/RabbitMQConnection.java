package com.kingcode.demo.connections;

import com.mycompany.main.lib.constants.RabbitMQConstants;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConnection {

    private AmqpAdmin admin;

    public RabbitMQConnection(AmqpAdmin admin) {
        this.admin = admin;
    }

    
    private Queue createEmailQueue() {
        return QueueBuilder.durable(RabbitMQConstants.EMAIL_QUEUE_NAME)
                .withArgument("x-dead-letter-exchange", RabbitMQConstants.DLQ_EMAIL_EXCHANGE_NAME) 
                .withArgument("x-dead-letter-routing-key", RabbitMQConstants.DLQ_EMAIL_ROUTING_KEY) 
                .withArgument("x-message-ttl", 10000) 
                .build();
    }

    private Queue createSMSQueue() {
        return QueueBuilder.durable(RabbitMQConstants.SMS_QUEUE_NAME)
                .withArgument("x-dead-letter-exchange", RabbitMQConstants.DLQ_SMS_EXCHANGE_NAME) 
                .withArgument("x-dead-letter-routing-key", RabbitMQConstants.DLQ_SMS_ROUTING_KEY) 
                .withArgument("x-message-ttl", 10000) 
                .build();
    }

    private Queue createResponseQueue(String queueName) {
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange createExchange(String exchangeName) {
        return new DirectExchange(exchangeName, true, false, null);
    }

    public Queue createDlqQueue(String queueName) {
        return QueueBuilder.durable(queueName).build();
    }

    private Binding createBinding(String queueName, String exchangeName, String routingKey) {
        return new Binding(queueName, Binding.DestinationType.QUEUE, exchangeName, routingKey, null);
    }

    public Binding dlqBinding(Queue dlqQueue, DirectExchange deadLetterExchange, String routingKey) {
        return BindingBuilder.bind(dlqQueue)
                .to(deadLetterExchange)
                .with(routingKey);
    }

    @PostConstruct
    private void connectionSettings() throws Exception {
        //creating queues
        Queue emailQueue = createEmailQueue();
        Queue emailResponseQueue = createResponseQueue(RabbitMQConstants.EMAIL_RESPONSE_QUEUE_NAME);
        Queue smsQueue = createSMSQueue();
        Queue smsResponseQueue = createResponseQueue(RabbitMQConstants.SMS_RESPONSE_QUEUE_NAME);
        Queue dqlEmailQueue = createDlqQueue(RabbitMQConstants.DLQ_EMAIL_QUEUE_NAME);
        Queue dqlSmsQueue = createDlqQueue(RabbitMQConstants.DLQ_SMS_QUEUE_NAME);

        //creating Exchange
        DirectExchange emailExchange = createExchange(RabbitMQConstants.EMAIL_EXCHANGE_NAME);
        DirectExchange emailResponseExchange = createExchange(RabbitMQConstants.EMAIL_RESPONSE_EXCHANGE_NAME);
        DirectExchange smsExchange = createExchange(RabbitMQConstants.SMS_EXCHANGE_NAME);
        DirectExchange smsResponseExchange = createExchange(RabbitMQConstants.SMS_RESPONSE_EXCHANGE_NAME);
        DirectExchange dlqEmailExchange = createExchange(RabbitMQConstants.DLQ_EMAIL_EXCHANGE_NAME);
        DirectExchange dlqSmsExchange = createExchange(RabbitMQConstants.DLQ_SMS_EXCHANGE_NAME);

        //creating Bindings
        Binding bindingEmail = createBinding(emailQueue.getName(), emailExchange.getName(), RabbitMQConstants.EMAIL_ROUTING_KEY);
        Binding bindingEmailResponse = createBinding(emailResponseQueue.getName(), emailResponseExchange.getName(), RabbitMQConstants.EMAIL_RESPONSE_ROUTING_KEY);
        Binding bindingSms = createBinding(smsQueue.getName(), smsExchange.getName(), RabbitMQConstants.SMS_ROUTING_KEY);
        Binding bindingSmsResponse = createBinding(smsResponseQueue.getName(), smsResponseExchange.getName(), RabbitMQConstants.SMS_RESPONSE_ROUTING_KEY);
        Binding bindingDLQEmailResponse = dlqBinding(dqlEmailQueue, dlqEmailExchange, RabbitMQConstants.DLQ_EMAIL_ROUTING_KEY);
        Binding bindingDLQSmsResponse = dlqBinding(dqlSmsQueue, dlqSmsExchange, RabbitMQConstants.DLQ_SMS_ROUTING_KEY);

        this.admin.declareQueue(emailQueue);
        this.admin.declareQueue(emailResponseQueue);
        this.admin.declareQueue(smsQueue);
        this.admin.declareQueue(smsResponseQueue);
        this.admin.declareQueue(dqlEmailQueue);
        this.admin.declareQueue(dqlSmsQueue);

        this.admin.declareExchange(emailExchange);
        this.admin.declareExchange(emailResponseExchange);
        this.admin.declareExchange(smsExchange);
        this.admin.declareExchange(smsResponseExchange);
        this.admin.declareExchange(dlqEmailExchange);
        this.admin.declareExchange(dlqSmsExchange);

        this.admin.declareBinding(bindingEmail);
        this.admin.declareBinding(bindingEmailResponse);
        this.admin.declareBinding(bindingSms);
        this.admin.declareBinding(bindingSmsResponse);
        this.admin.declareBinding(bindingDLQEmailResponse);
        this.admin.declareBinding(bindingDLQSmsResponse);

    }

}
