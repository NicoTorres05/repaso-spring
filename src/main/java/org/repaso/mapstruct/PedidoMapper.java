package org.repaso.mapstruct;

import org.repaso.dto.PedidoDTO;
import org.repaso.model.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    @Mapping(target = "nombreCliente", source = "nombreCliente")
    @Mapping(target = "nombreComercial", source = "nombreComercial")

    public PedidoDTO pedidoAPedidoDTO(Pedido pedido, String nombreCliente, String nombreComercial);

    public Pedido pedidoDTOAPedido(PedidoDTO pedido);
}
