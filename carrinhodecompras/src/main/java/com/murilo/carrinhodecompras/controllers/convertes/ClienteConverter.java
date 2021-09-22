package com.murilo.carrinhodecompras.controllers.convertes;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.murilo.carrinhodecompras.controllers.ClienteController;
import com.murilo.carrinhodecompras.models.Cliente;


@FacesConverter("clienteConverter")
public class ClienteConverter implements Converter<Cliente>, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final ClienteController clienteController;
	
	public ClienteConverter() {
		FacesContext context = FacesContext.getCurrentInstance();
		clienteController = (ClienteController) context.getELContext().getELResolver().getValue(context.getELContext(), 
				null, 
				"clienteController");
	}
	
	@Override
	public Cliente getAsObject(FacesContext context, UIComponent component, String value) {
		//if(value != null && !value.isEmpty()) {
		//	return  clienteRepository.porId(Long.parseLong(value));
		//}
		//return null;
		Cliente cliente =clienteController.buscarPorId(new Long(value));

		return cliente;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Cliente value) {
		if(value != null) {
			Cliente c = (Cliente) value;
			return c.getId() != null && c.getId() > 0 ? c.getId().toString() : null;
		}
		return null;
	}

}
