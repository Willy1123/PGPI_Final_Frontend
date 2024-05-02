package org.vaadin.example;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.router.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 * <p>
 * The main view contains a text field for getting the user name and a button
 * that shows a greeting message in a notification.
 */
@Route
@PageTitle("Ethical Tech Nexus")
public class MainView extends VerticalLayout {

    private VerticalLayout tab1Content = new VerticalLayout();
    private HorizontalLayout tab1ContentH = new HorizontalLayout();
    private VerticalLayout tab2Content = new VerticalLayout();
    private VerticalLayout tab3Content = new VerticalLayout();
    private HorizontalLayout tab3ContentH = new HorizontalLayout();
    private Button btn_AddPoblation = new Button("New");
    private Button btn_AddProduct = new Button("New");
    private Button btn_ReloadPoblation = new Button("Reload");
    private Button btn_ReloadProduct = new Button("Reload");

    public VerticalLayout btnAddPoblation(GreetService service){
        VerticalLayout layoutarriba = new VerticalLayout();

        // Al pulsar el botón, aparece un formulario para rellenar los campos y otro botón para añadir los elementos escritos
        Binder<ndData> binder = new Binder<>(ndData.class);
        HorizontalLayout LFormAniadir = new HorizontalLayout();

        // Campo de texto para msCode
        TextField msCodeField2 = new TextField();
        msCodeField2.setLabel("msCode");
        msCodeField2.setPlaceholder("MEASA");
        msCodeField2.isRequired();

        // Campo de texto para año
        TextField yearField2 = new TextField();
        yearField2.setLabel("year");
        yearField2.setPlaceholder("2024");
        yearField2.isRequired();

        // Campo de texto para estCode
        TextField estCodeField2 = new TextField();
        estCodeField2.setLabel("estCode");
        estCodeField2.setPlaceholder("_Number");
        estCodeField2.isRequired();

        // Campo de texto para estimación
        TextField estimateField2 = new TextField();
        estimateField2.setLabel("estimate");
        estimateField2.setPlaceholder("0.0");
        estimateField2.isRequired();

        // Campo de texto para se
        TextField seField2 = new TextField();
        seField2.setLabel("se");
        seField2.setPlaceholder("0.0");
        seField2.isRequired();

        // Campo de texto para lowerCIB
        TextField lowerCIBField2 = new TextField();
        lowerCIBField2.setLabel("lowerCIB");
        lowerCIBField2.setPlaceholder("0.0");
        lowerCIBField2.isRequired();

        // Campo de texto para upperCIB
        TextField upperCIBField2 = new TextField();
        upperCIBField2.setLabel("upperCIB");
        upperCIBField2.setPlaceholder("0.0");
        upperCIBField2.isRequired();

        // Campo de texto para flag
        TextField flagField2 = new TextField();
        flagField2.setLabel("flag");
        flagField2.setPlaceholder("A");
        flagField2.isRequired();

        // Botón para guardar la información
        Button saveNewPoblation = new Button("Aceptar", e2 -> {
            ndData edicion = new ndData();
            edicion.setID(UUID.randomUUID().toString());
            edicion.setMsCode(msCodeField2.getValue());
            edicion.setYear(yearField2.getValue());
            edicion.setEstCode(estCodeField2.getValue());
            edicion.setEstimate(Float.parseFloat(estimateField2.getValue()));
            edicion.setSe(Float.parseFloat(seField2.getValue()));
            edicion.setLowerCIB(Float.parseFloat(lowerCIBField2.getValue()));
            edicion.setUpperCIB(Float.parseFloat(upperCIBField2.getValue()));
            edicion.setFlag(flagField2.getValue());
            try {
                service.postData(edicion);
                UI.getCurrent().getPage().reload();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // Botón para cancelar la operación
        Button cancelNewPoblation = new Button("Cancelar", e2 -> {
            tab1ContentH.removeAll();
            tab1Content.removeAll();
            try {
                tab1ContentH.add(btn_ReloadPoblation, btn_AddPoblation);
                tab1Content.add(tab1ContentH,MostrarGrid(service));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // Añadiendo los campos y botones al formulario
        LFormAniadir.add(msCodeField2,yearField2,estCodeField2,estimateField2,seField2,lowerCIBField2,upperCIBField2,flagField2,saveNewPoblation,cancelNewPoblation);
        layoutarriba.add(LFormAniadir);

        return layoutarriba;
    }

    public VerticalLayout btnAddProduct(GreetService service){
        VerticalLayout layoutarriba = new VerticalLayout();

        // Al pulsar el botón, aparece un formulario para rellenar los campos y otro botón para añadir los elementos escritos
        Binder<Products> binder = new Binder<>(Products.class);
        HorizontalLayout lytFormAddProduct = new HorizontalLayout();

        // Campo de texto para nombre del producto
        TextField tfProductName = new TextField();
        tfProductName.setLabel("Name");
        tfProductName.setPlaceholder("Mantas");
        tfProductName.isRequired();

        // Campo de texto para Descripción del producto
        TextField tfProductDesc = new TextField();
        tfProductDesc.setLabel("Description");
        tfProductDesc.setPlaceholder("Mantas de lana para uso en climas fríos");
        tfProductDesc.isRequired();

        // Campo de texto para cantidad de Stock del pordocut
        TextField tfProductStock = new TextField();
        tfProductStock.setLabel("Stock");
        tfProductStock.setPlaceholder("500");
        tfProductStock.isRequired();

        // Campo de texto para Fecha de caducidad del producto
        TextField tfProductExpDate = new TextField();
        tfProductExpDate.setLabel("Expiration Date");
        tfProductExpDate.setPlaceholder("aaaa-mm-dd or NULL");
        tfProductExpDate.isRequired();

        // Botón para guardar la información
        Button saveNewProduct = new Button("Aceptar", e2 -> {
            Products edicionProducto = new Products();
            edicionProducto.setId(UUID.randomUUID().toString());
            edicionProducto.setName(tfProductName.getValue());
            edicionProducto.setDescription(tfProductDesc.getValue());
            edicionProducto.setStock(Float.parseFloat(tfProductStock.getValue()));
            edicionProducto.setExpiration_date(tfProductExpDate.getValue());
            try {
                service.postProduct(edicionProducto);
                UI.getCurrent().getPage().reload();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // Botón para cancelar la operación
        Button cancelNewProduct = new Button("Cancel", e2 -> {
            tab3ContentH.removeAll();
            tab3Content.removeAll();
            try {
                tab3ContentH.add(btn_ReloadProduct, btn_AddProduct);
                tab3Content.add(tab3ContentH,MostrarGridProductos(service));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // Añadiendo los campos y botones al formulario
        lytFormAddProduct.add(tfProductName,tfProductDesc,tfProductStock,tfProductExpDate,saveNewProduct,cancelNewProduct);
        layoutarriba.add(lytFormAddProduct);

        return layoutarriba;
    }

    public VerticalLayout MostrarGrid(GreetService service) throws Exception {
        VerticalLayout LayoutGrid = new VerticalLayout();

        // Crear un nuevo grid para la clase ndData
        Grid<ndData> grid = new Grid<>(ndData.class, false);

        // Crear un editor para el grid
        Editor<ndData> editor = grid.getEditor();

        // Añadir columnas al grid con sus respectivos encabezados
        Grid.Column<ndData> idColumn = grid.addColumn(ndData::getID).setHeader("ID");
        Grid.Column<ndData> msCodeColumn = grid.addColumn(ndData::getMsCode).setHeader("Ms Code");
        Grid.Column<ndData> yearColumn = grid.addColumn(ndData::getYear).setHeader("Year");
        Grid.Column<ndData> estCodeColumn = grid.addColumn(ndData::getEstCode).setHeader("Est Code");
        Grid.Column<ndData> estimateColumn = grid.addColumn(ndData::getEstimate).setHeader("Estimate");
        Grid.Column<ndData> seColumn = grid.addColumn(ndData::getSe).setHeader("Se");
        Grid.Column<ndData> lowerCIBColumn = grid.addColumn(ndData::getLowerCIB).setHeader("Lower CIB");
        Grid.Column<ndData> upperCIBColumn = grid.addColumn(ndData::getUpperCIB).setHeader("Upper CIB");
        Grid.Column<ndData> flagColumn = grid.addColumn(ndData::getFlag).setHeader("Flag");

        // Columna para editar cada fila
        Grid.Column<ndData> editorColumn = grid.addComponentColumn(ndData -> {
            Button edit = new Button("Edit");
            edit.addClassName("edit");
            edit.addClickListener(e -> {
                editor.editItem(ndData);
            });
            edit.setEnabled(!editor.isOpen());
            return edit;
        });

        // Crear un Binder para vincular datos
        Binder<ndData> binder = new Binder<>(ndData.class);
        editor.setBinder(binder);
        editor.setBuffered(true);

        // Configurar campos de texto y su validación
        // Para cada campo se especifica la necesidad de llenado y se vincula al grid

        TextField idField = new TextField();
        binder.forField(idField)
                .asRequired("ID is required")
                .bind(ndData::getID, ndData::setID);

        TextField msCodeField = new TextField();
        binder.forField(msCodeField)
                .asRequired("Ms Code necesario")
                .bind(ndData::getMsCode, ndData::setMsCode);
        msCodeColumn.setEditorComponent(msCodeField);

        TextField yearField = new TextField();
        binder.forField(yearField)
                .asRequired("Year necesario")
                .bind(ndData::getYear, ndData::setYear);
        yearColumn.setEditorComponent(yearField);

        TextField estCodeField = new TextField();
        binder.forField(estCodeField)
                .asRequired("Est Code necesario")
                .bind(ndData::getEstCode, ndData::setEstCode);
        estCodeColumn.setEditorComponent(estCodeField);

        // Al editar campos utilizamos el StringToFloatConverter para convertir el String introducido al usuario a Float
        TextField estimateField = new TextField();
        binder.forField(estimateField).withConverter(new StringToFloatConverter("Debe ingresar un número"))
                .asRequired("Estimate necesario")
                .bind(ndData::getEstimate, ndData::setEstimate);
        estimateColumn.setEditorComponent(estimateField);

        TextField seField = new TextField();
        binder.forField(seField).withConverter(new StringToFloatConverter("Debe ingresar un número"))
                .asRequired("Se necesario")
                .bind(ndData::getSe, ndData::setSe);
        seColumn.setEditorComponent(seField);

        TextField lowerCIBField = new TextField();
        binder.forField(lowerCIBField).withConverter(new StringToFloatConverter("Debe ingresar un número"))
                .asRequired("Lower CIB necesario")
                .bind(ndData::getLowerCIB, ndData::setLowerCIB);
        lowerCIBColumn.setEditorComponent(lowerCIBField);

        TextField upperCIBField = new TextField();
        binder.forField(upperCIBField).withConverter(new StringToFloatConverter("Debe ingresar un número"))
                .asRequired("Upper CIB necesario")
                .bind(ndData::getUpperCIB, ndData::setUpperCIB);
        upperCIBColumn.setEditorComponent(upperCIBField);

        TextField flagField = new TextField();
        binder.forField(flagField)
                .bind(ndData::getFlag, ndData::setFlag);
        flagColumn.setEditorComponent(flagField);

        // Configurar el bean para el binder
        binder.setBean(new ndData());

        // Botones para guardar o cancelar la edición
        Button save = new Button("Aceptar", e -> {
            // Actualizar el bean con los datos del editor
            ndData edicion = new ndData();
            edicion.setID(idField.getValue());
            edicion.setMsCode(msCodeField.getValue());
            edicion.setYear(yearField.getValue());
            edicion.setEstCode(estCodeField.getValue());
            edicion.setEstimate(Float.parseFloat(estimateField.getValue()));
            edicion.setSe(Float.parseFloat(seField.getValue()));
            edicion.setLowerCIB(Float.parseFloat(lowerCIBField.getValue()));
            edicion.setUpperCIB(Float.parseFloat(upperCIBField.getValue()));
            edicion.setFlag(flagField.getValue());
            // Actualizar los datos en la base de datos
            try {
                service.editData(edicion);
                UI.getCurrent().getPage().reload();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        Button cancel = new Button(VaadinIcon.CLOSE.create(), e -> editor.cancel());
        cancel.addThemeVariants(ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_ERROR);
        HorizontalLayout actions = new HorizontalLayout(save, cancel);
        actions.setPadding(false);
        editorColumn.setEditorComponent(actions);

        // Establecer los items del grid y añadir el grid al layout
        grid.setItems(service.Getdata());
        LayoutGrid.add(grid);
        LayoutGrid.setSizeFull();

        return LayoutGrid;
    }

    public VerticalLayout MostrarGridProductos(GreetService service) throws Exception {
        VerticalLayout LayoutGrid = new VerticalLayout();

        // Crear un nuevo grid para la clase ndData
        Grid<Products> productGrid = new Grid<>(Products.class, false);

        // Crear un editor para el grid
        Editor<Products> productsEditor = productGrid.getEditor();

        // Añadir columnas al grid con sus respectivos encabezados
        Grid.Column<Products> idColumn = productGrid.addColumn(Products::getId).setHeader("ID");
        Grid.Column<Products> msCodeColumn = productGrid.addColumn(Products::getName).setHeader("Name");
        Grid.Column<Products> yearColumn = productGrid.addColumn(Products::getDescription).setHeader("Description");
        Grid.Column<Products> estCodeColumn = productGrid.addColumn(Products::getStock).setHeader("Stock");
        Grid.Column<Products> estimateColumn = productGrid.addColumn(Products::getExpiration_date).setHeader("Expiration Date");

        // Columna para editar cada fila
        Grid.Column<Products> editorProductColumn = productGrid.addComponentColumn(products -> {
            Button edit = new Button("Edit");
            edit.addClassName("edit");
            edit.addClickListener(e -> {
                productsEditor.editItem(products);
            });
            edit.setEnabled(!productsEditor.isOpen());
            return edit;
        });

        // Crear un Binder para vincular datos
        Binder<Products> binder = new Binder<>(Products.class);
        productsEditor.setBinder(binder);
        productsEditor.setBuffered(true);

        // Configurar campos de texto y su validación
        // Para cada campo se especifica la necesidad de llenado y se vincula al grid

        TextField productNameField = new TextField();
        binder.forField(productNameField)
                .asRequired("Name must need it")
                .bind(Products::getName, Products::setName);
        msCodeColumn.setEditorComponent(productNameField);

        TextField porductDescField = new TextField();
        binder.forField(porductDescField)
                .asRequired("Desc must need it")
                .bind(Products::getDescription, Products::setDescription);
        yearColumn.setEditorComponent(porductDescField);

        TextField productStockField = new TextField();
        binder.forField(productStockField).withConverter(new StringToFloatConverter("You have to input a Number"))
                .asRequired("Stock must need it")
                .bind(Products::getStock, Products::setStock);
        estCodeColumn.setEditorComponent(productStockField);

        // Al editar campos utilizamos el StringToFloatConverter para convertir el String introducido al usuario a Float
        TextField productExpirationDateField = new TextField();
        binder.forField(productExpirationDateField)
                .asRequired("Estimate necesario")
                .bind(Products::getExpiration_date, Products::setExpiration_date);
        estimateColumn.setEditorComponent(productExpirationDateField);

        // Configurar el bean para el binder
        binder.setBean(new Products());

        // Botones para guardar o cancelar la edición
        Button productSave = new Button("Aceptar", e -> {
            // Actualizar el bean con los datos del editor
            Products editProduct = new Products();
            editProduct.setName(productNameField.getValue());
            editProduct.setDescription(porductDescField.getValue());
            editProduct.setStock(Float.parseFloat(productStockField.getValue()));
            editProduct.setExpiration_date(productExpirationDateField.getValue());
            // Actualizar los datos en la base de datos
            try {
                service.editProduct(editProduct);
                UI.getCurrent().getPage().reload();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        Button cancel = new Button(VaadinIcon.CLOSE.create(), e -> productsEditor.cancel());
        cancel.addThemeVariants(ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_ERROR);
        HorizontalLayout actions = new HorizontalLayout(productSave, cancel);
        actions.setPadding(false);
        editorProductColumn.setEditorComponent(actions);

        // Establecer los items del grid y añadir el grid al layout
        productGrid.setItems(service.GetProduct());
        LayoutGrid.add(productGrid);
        LayoutGrid.setSizeFull();

        return LayoutGrid;
    }

    public VerticalLayout GridMsC(GreetService service) throws Exception{

        VerticalLayout LGMsC = new VerticalLayout();
        // Creamos un Grid para mostrar los datos
        Grid<ndData> gridMsC = new Grid<>(ndData.class, false);
        Grid.Column<ndData> IDMscColumn = gridMsC.addColumn(ndData::getID).setHeader("ID");
        Grid.Column<ndData> msCodeMscColumn = gridMsC.addColumn(ndData::getMsCode).setHeader("Ms Code");
        Grid.Column<ndData> yearMscColumn = gridMsC.addColumn(ndData::getYear).setHeader("Year");
        Grid.Column<ndData> estCodeMscColumn = gridMsC.addColumn(ndData::getEstCode).setHeader("Est Code");
        Grid.Column<ndData> estimateMscColumn = gridMsC.addColumn(ndData::getEstimate).setHeader("Estimate");
        Grid.Column<ndData> seMscColumn = gridMsC.addColumn(ndData::getSe).setHeader("Se");
        Grid.Column<ndData> lowerCIBMscColumn = gridMsC.addColumn(ndData::getLowerCIB).setHeader("Lower CIB");
        Grid.Column<ndData> upperCIBMscColumn = gridMsC.addColumn(ndData::getUpperCIB).setHeader("Upper CIB");
        Grid.Column<ndData> flagMscColumn = gridMsC.addColumn(ndData::getFlag).setHeader("Flag");


        // Creamos un ComboBox para filtrar por MsCode
        ComboBox<String> filterComboBox = new ComboBox<>();

        // Establecer el placeholder del ComboBox
        filterComboBox.setPlaceholder("Filtrar por MsCode");

        // Obtener todos los MsCode únicos de los datos
        List<String> uniqueMsCodes = service.Getdata().stream()
                .map(ndData::getMsCode)
                .distinct()
                .collect(Collectors.toList());

        // Añadir los MsCode únicos al ComboBox
        filterComboBox.setItems(uniqueMsCodes);

        // Crear un botón para aplicar el filtro
        Button filterButton = new Button("Filtrar", clickEvent -> {
            // Obtener el valor seleccionado en el ComboBox
            String filterValue = filterComboBox.getValue();
            List<ndData> filteredData;
            try {
                // Filtrar los datos basándose en el valor seleccionado en el ComboBox
                filteredData = service.Getdata().stream()
                        .filter(ndData -> ndData.getMsCode().equals(filterValue))
                        .collect(Collectors.toList());
                // Actualizar el Grid con los datos filtrados
                gridMsC.setItems(filteredData);
            } catch (Exception e) {
                // Imprimir la traza de la excepción en caso de error
                e.printStackTrace();
            }
        });

        // Añadir los objetos desde la API al grid
        List<ndData> ndData = service.GetDataMsC();
        gridMsC.setItems(ndData);
        // Añadir los objetos al layout
        LGMsC.add(filterComboBox,filterButton,gridMsC);
        LGMsC.setSizeFull();
        return LGMsC;
    }

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service
     *            The message service. Automatically injected Spring managed
     *            bean.
     */
    public MainView(@Autowired GreetService service) throws Exception {

        // Creamos las pestañas
        Tab tab1 = new Tab("[AV] Población Objetivo");
        Tab tab2 = new Tab("[CV] Población Objetivo]");
        Tab tab3 = new Tab("[AV] Products");

        // Creamos el contenedor de las pestañas
        Tabs tabs = new Tabs(tab1, tab2, tab3);

        // Creamos el contenido de las pestañas
        tab1Content.setSizeFull();
        tab2Content.setSizeFull();
        tab3Content.setSizeFull();

        //boton de añadir elementos a la población objetiva
        btn_AddPoblation.addClickListener(e -> {
            try {
                tab1Content.removeAll();
                tab1Content.add(btn_ReloadPoblation, btnAddPoblation(service), MostrarGrid(service));

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        //boton de añadir elementos a la BBDD de productos
        btn_AddProduct.addClickListener(e -> {
            try {
                tab3Content.removeAll();
                tab3Content.add(btn_ReloadProduct, btnAddProduct(service), MostrarGridProductos(service));

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        //boton para recargar las tablas
        btn_ReloadPoblation.addClickListener(e -> {
            try {
                tab1ContentH.removeAll();
                tab1Content.removeAll();
                tab1ContentH.add(btn_ReloadPoblation, btn_AddPoblation);
                tab1Content.add(tab1ContentH,MostrarGrid(service));

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        //boton para recargar las tablas
        btn_ReloadProduct.addClickListener(e -> {
            try {
                tab3ContentH.removeAll();
                tab3Content.removeAll();
                tab3ContentH.add(btn_ReloadProduct, btn_AddProduct);
                tab3Content.add(tab3ContentH,MostrarGridProductos(service));

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // Creamos el contenido para cada pestaña
        tab1ContentH.add(btn_ReloadPoblation, btn_AddPoblation);
        tab1Content.add(tab1ContentH, MostrarGrid(service));
        tab2Content.add(GridMsC(service));
        tab3ContentH.add(btn_ReloadProduct, btn_AddProduct);
        tab3Content.add(tab3ContentH, MostrarGridProductos(service));
        tab1Content.setVisible(true);
        tab2Content.setVisible(false);
        tab3Content.setVisible(false);

        // Cambiamos el contenido visible según la pestaña seleccionada
        tabs.addSelectedChangeListener(event -> {
            if (tabs.getSelectedTab() == tab1) {
                tab1Content.setVisible(true);
                tab2Content.setVisible(false);
                tab3Content.setVisible(false);
            }

            if (tabs.getSelectedTab() == tab2) {
                tab1Content.setVisible(false);
                tab2Content.setVisible(true);
                tab3Content.setVisible(false);
            }
            if (tabs.getSelectedTab() == tab3) {
                tab1Content.setVisible(false);
                tab2Content.setVisible(false);
                tab3Content.setVisible(true);
            }
        });

        // Añadimos las pestañas
        add(tabs, tab1Content, tab2Content, tab3Content);
        setSizeFull();


    }
}
