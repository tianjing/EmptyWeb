package freemarker.ext.beans;


import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import tgtools.data.DataRow;
import tgtools.data.DataRowCollection;
import tgtools.data.DataTable;

public class TableWrapper extends BeansWrapper {

	@Override
	public TemplateModel wrap(Object object) throws TemplateModelException {
		if (object instanceof DataRow)
			return ((TemplateModel) DataRowModel.FACTORY.create(object, this));
		if (object instanceof DataRowCollection)
			return ((TemplateModel) DataTableModel.FACTORY.create(object, this));
		//if(object instanceof Map)
		//	return DataTableMapModel.FACTORY.create(object, this);
		if(object instanceof DataTable)
			return DataTableModel.FACTORY.create(((DataTable)object), this);
		TemplateModel res= super.wrap(object);
		return res;
	}

	@Override
	public Object unwrap(TemplateModel model) throws TemplateModelException {
		if (model instanceof DataRowModel)
			return ((DataRowModel) model).getWrappedObject();
		if (model instanceof DataTableModel)
			return ((DataTableModel) model).getWrappedObject();
		return super.unwrap(model);
	}



}
