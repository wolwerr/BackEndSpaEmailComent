package com.ms.coment.consumers;

import com.ms.coment.dtos.ComentDto;
import com.ms.coment.models.ComentModel;
import com.ms.coment.services.ComentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ComentConsumer {
    @Autowired
    ComentService comentService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload ComentDto comentDto) {
        ComentModel comentModel = new ComentModel();
        BeanUtils.copyProperties(comentDto, comentModel);
        comentService.sendComent(comentModel);
        System.out.println("Coment Status: " + comentModel.getStatusComent().toString());
    }
}
