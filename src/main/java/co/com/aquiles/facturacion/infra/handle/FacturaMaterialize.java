package co.com.aquiles.facturacion.infra.handle;

import co.com.aquiles.facturacion.domain.factura.events.*;
import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.logging.Logger;

@Component
public class FacturaMaterialize {
    private static final String COLLECTION_NAME = "facturas";
    private static final Logger logger = Logger.getLogger(FacturaMaterialize.class.getName());

    @Autowired
    @Qualifier("mongoTemplateQueries")
    private MongoTemplate mongoTemplate;

    @Async
    @EventListener
    public void handleEventFacturaCreada(FacturaCreada facturaCreada) {
        logger.info("****** Handle event facturaCreada");
        Map<String, Object> data = new HashMap<>();
        data.put("_id", facturaCreada.facturaId().value());
        data.put("productoEnvido", false);
        mongoTemplate.save(data, COLLECTION_NAME);
    }

    @Async
    @EventListener
    public void handleEventTiendaAsignada(TiendaAsignada tiendaAsignada) {
        logger.info("****** Handle event tiendaAsignada");
        Update update = new Update();
        var id = tiendaAsignada.tiendaId().value();
        update.set("tienda."+id+".descuento", tiendaAsignada.descuento().value());

        mongoTemplate.updateFirst(getFilterByAggregateId(tiendaAsignada), update, COLLECTION_NAME);
    }

    @Async
    @EventListener
    public void handleEventClienteAsignado(ClienteAsignado clienteAsignado) {
        logger.info("****** Handle event clienteAsignado");
        Update update = new Update();
        var id = clienteAsignado.cedula().value();
        update.set("cliente."+id+".nombre", clienteAsignado.nombre().value());
        mongoTemplate.updateFirst(getFilterByAggregateId(clienteAsignado), update, COLLECTION_NAME);
    }

    @Async
    @EventListener
    public void handleEventProductosAsignados(ProductosAsignados productosAsignados) {
        logger.info("****** Handle event productosAsignados");
        Update update = new Update();
        var id = productosAsignados.factory().productos();
        update.set("cliente."+id+".nombre", productosAsignados.factory().productos());
        mongoTemplate.updateFirst(getFilterByAggregateId(productosAsignados), update, COLLECTION_NAME);
    }


    @Async
    @EventListener
    public void handleEventProductosEviado(ProductosEnviado productosEnviado) {
        logger.info("****** Handle event productosEnviado");
        Update update = new Update();
        update.set("productoEnvido" , true);
        mongoTemplate.updateFirst(getFilterByAggregateId(productosEnviado), update, COLLECTION_NAME);
    }


    private Query getFilterByAggregateId(DomainEvent event) {
        return new Query(
                Criteria.where("_id").is(event.aggregateRootId())
        );
    }
}
