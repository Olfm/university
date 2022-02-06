package web.controllers; import web.models.Cathedra;
import web.controllers.util.JsfUtil;
import web.controllers.util.JsfUtil.PersistAction; import web.models.facades.CathedraFacade;

CathedraController controller = (CathedraController) facesCon-text.getApplication().getELResolver().
getValue(facesContext.getELContext(), null, "cathedraController"); return con-troller.getCathedra(getKey(value));
}
package web.controllers;

import web.models.EduForm; import web.controllers.util.JsfUtil;
import web.controllers.util.JsfUtil.PersistAction; import web.models.facades.EduFormFacade;

import java.io.Serializable; import java.util.List;
import java.util.ResourceBundle; import java.util.logging.Level; import ja-va.util.logging.Logger; import javax.ejb.EJB;
import javax.ejb.EJBException; import javax.inject.Named;
import javax.enterprise.context.SessionScoped; import ja-vax.faces.component.UIComponent; import javax.faces.context.FacesContext; import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("eduFormController") @SessionScoped
public class EduFormController implements Serializable {
 
@EJB
private web.models.facades.EduFormFacade ejbFacade; private List<EduForm> items = null;
private EduForm selected;

public EduFormController() {
}

public EduForm getSelected() { return selected;
}

public void setSelected(EduForm selected) { this.selected = selected;
}

protected void setEmbeddableKeys() {
}

protected void initializeEmbeddableKey() {
}

private EduFormFacade getFacade() { return ejbFacade;
}

public EduForm prepareCreate() { selected = new EduForm(); initializeEm-beddableKey(); return selected;
}

public void create() { persist(PersistAction.CREATE,
Resource-Bundle.getBundle("web/resources/Bundle").getString("SuccessfullyCreat ed"));
if (!JsfUtil.isValidationFailed()) {
items = null; // Invalidate list of items to trigger re-query.
}
}

public void update() {
 
persist(PersistAction.UPDATE, Resource-Bundle.getBundle("web/resources/Bundle").getString("SuccessfullyUpda ted"));
}


@Inject
private ScheduleController scheduleController;

@EJB
private web.models.facades.EduGroupFacade ejbFacade; private List<EduGroup> items = null;
private EduGroup selected;

public EduGroupController() {
}

public EduGroup getSelected() { return selected;
}

public void setSelected(EduGroup selected) { this.selected = selected;
}

protected void setEmbeddableKeys() {
}

protected void initializeEmbeddableKey() {
}

private EduGroupFacade getFacade() { return ejbFacade;
}

public EduGroup prepareCreate() { selected = new EduGroup();
selected.setIdSchedule(scheduleController.getSelected()); initializeEmbedda-bleKey();
return selected;
}
 
public void create() { persist(PersistAction.CREATE,
Resource-Bundle.getBundle("web/resources/Bundle").getString("SuccessfullyCreat ed"));
if (!JsfUtil.isValidationFailed()) {
items = null; // Invalidate list of items to trigger re-query.
}
}

public void update() { persist(PersistAction.UPDATE,
Resource-Bundle.getBundle("web/resources/Bundle").getString("SuccessfullyUpda ted"));
}

public void destroy() { persist(PersistAction.DELETE,
Resource-Bundle.getBundle("web/resources/Bundle").getString("SuccessfullyDelet ed"));
if (!JsfUtil.isValidationFailed()) { selected = null; // Remove selection
items = null; // Invalidate list of items to trigger re-query.
}
}

public List<EduGroup> getItems() { items = null;
if (items == null) { items =
getFa-cade().getEntityManager().createNamedQuery("EduGroup.findByIdSchedule ").setParameter("idSchedule", scheduleControl-ler.getSelected().getId()).getResultList();
}
return items;
}

public List<Subject> getItems() { if (items == null) {
items = getFacade().findAll();
}
 
return items;
}
private void persist(PersistAction persistAction, String successMessage) { if (selected != null) {
setEmbeddableKeys(); try {
if (persistAction != PersistAction.DELETE) { getFacade().edit(selected);
} else { getFacade().remove(selected);
}
JsfUtil.addSuccessMessage(successMessage);
} catch (EJBException ex) { String msg = "";
Throwable cause = ex.getCause(); if (cause != null) {
msg = cause.getLocalizedMessage();
}
if (msg.length() > 0) { JsfUtil.addErrorMessage(msg);
} else { JsfUtil.addErrorMessage(ex,
Resource-Bundle.getBundle("web/resources/Bundle").getString("PersistenceErrorO ccured"));
}
} catch (Exception ex) {
 
ex);
 
Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null,

JsfUtil.addErrorMessage(ex,
 Resource-Bundle.getBundle("web/resources/Bundle").getString("PersistenceErrorO ccured"));
}
}
}

public Subject getSubject(java.lang.Integer id) { return getFacade().find(id);
}

public List<Subject> getItemsAvailableSelectMany() { return getFa-cade().findAll();
}

public List<Subject> getItemsAvailableSelectOne() { return getFa-cade().findAll();
}

@FacesConverter(forClass = Subject.class)
public static class SubjectControllerConverter implements Converter {

}.
