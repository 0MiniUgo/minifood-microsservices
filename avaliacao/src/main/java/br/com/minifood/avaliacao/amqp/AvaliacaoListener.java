package br.com.minifood.avaliacao.amqp;

import br.com.minifood.avaliacao.dto.PagamentoDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoListener {

    @RabbitListener(queues = "pagamentos.detalhes-avaliacao")
    public void recebeMensagem(@Payload PagamentoDto pagamento) {

        System.out.println(pagamento.getId());
        System.out.println(pagamento.getNumero());

        if ("0000".equals(pagamento.getNumero())) {
            throw new RuntimeException("não consegui processar" + pagamento.getNumero());
        }

        String mensagem = """
                Necessário criar registro de avaliação para o pedido: %s 
                Id do pagamento: %s
                Nome do cliente: %s
                Numero do pedido: %s
                Valor R$: %s
                Status: %s 
                """.formatted(pagamento.getPedidoId(),
                pagamento.getId(),
                pagamento.getNome(),
                pagamento.getNumero(),
                pagamento.getValor(),
                pagamento.getStatus());

        System.out.println(mensagem);
    }
}
