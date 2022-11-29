package br.com.minifood.pedidos.amqp;

import br.com.minifood.pedidos.dto.PagamentoDto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PagamentoListener {

    @RabbitListener(queues = "pagamentos.detalhes-pedido")
    public void rececbeMensagem(PagamentoDto pagamento){

        String mensagem = """
                Dados do pagamento: %s
                Numero do pedido: %s
                Valor R$: %s
                Status: %s
                """.formatted(pagamento.getId(),
                    pagamento.getPedidoId(),
                    pagamento.getValor(),
                    pagamento.getStatus());

        System.out.println("Recebi a mensagem: " + mensagem);

    }
}
