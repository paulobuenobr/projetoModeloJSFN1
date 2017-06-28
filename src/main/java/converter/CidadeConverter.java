package converter;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Cidade;
import service.CidadeService;

/*
 * Um custom converter JSF deve implementar a interface Converter e os
 * métodos getAsObject e getAsString
 */

@Named
@ApplicationScoped
@FacesConverter(value = "cidadeConverter")
public class CidadeConverter implements Converter {

	@Inject
	private CidadeService cidadeService;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {

		if (value == null || value.isEmpty()) {
			return null;
		}
		try {
			Object obj = cidadeService.pesquisarPorId(Integer.valueOf(value));
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
//			throw new ConverterException(
//					new FacesMessage(String.format("Não é possível converter %s para Cidade", value)), e);
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		if (!(value instanceof Cidade)) {
			return null;
		}
		String s = String.valueOf(((Cidade) value).getId());
		return s;
	}

}