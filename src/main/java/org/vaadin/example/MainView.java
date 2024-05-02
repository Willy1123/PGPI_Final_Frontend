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
@PageTitle("DIS Práctica 2")
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service
     *            The message service. Automatically injected Spring managed
     *            bean.
     */
    public VerticalLayout botonañadir(GreetService service){
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
        Button save2 = new Button("Aceptar", e2 -> {
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
        Button cancel2 = new Button("Cancelar", e2 -> {
            UI.getCurrent().getPage().reload();
        });

        // Añadiendo los campos y botones al formulario
        LFormAniadir.add(msCodeField2,yearField2,estCodeField2,estimateField2,seField2,lowerCIBField2,upperCIBField2,flagField2,save2,cancel2);
        layoutarriba.add(LFormAniadir);

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

    public MainView(@Autowired GreetService service) throws Exception {

        // Creamos las pestañas
        Tab tab1 = new Tab("Parte 1");
        Tab tab2 = new Tab("Parte 2");

        // Creamos el contenedor de las pestañas
        Tabs tabs = new Tabs(tab1, tab2);

        // Creamos el contenido de las pestañas
        VerticalLayout tab1Content = new VerticalLayout();
        tab1Content.setSizeFull();

        VerticalLayout tab2Content = new VerticalLayout();
        tab2Content.setSizeFull();

        //boton de añadir elementos
        Button botonAñadir = new Button("Añadir");
        botonAñadir.addClickListener(e -> {
            try {
                tab1Content.removeAll();
                tab1Content.add(botonañadir(service),MostrarGrid(service));

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // Creamos el contenido para cada pestaña
        tab1Content.add(botonAñadir, MostrarGrid(service));
        tab2Content.add(GridMsC(service));
        tab1Content.setVisible(true);
        tab2Content.setVisible(false);

        // Cambiamos el contenido visible según la pestaña seleccionada
        tabs.addSelectedChangeListener(event -> {
            if (tabs.getSelectedTab() == tab1) {
                tab1Content.setVisible(true);
                tab2Content.setVisible(false);
            } else {
                tab1Content.setVisible(false);
                tab2Content.setVisible(true);
            }
        });

        // Añadimos las pestañas
        add(tabs, tab1Content, tab2Content);
        setSizeFull();


    }
}
