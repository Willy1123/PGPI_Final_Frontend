package org.vaadin.example;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

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
@Route("UserView")
@PageTitle("Ethical Tech Nexus")
public class UserView extends VerticalLayout {

    // Tabs
    private final VerticalLayout tab2Content = new VerticalLayout();
    private final HorizontalLayout tab2ContentH = new HorizontalLayout();
    private final VerticalLayout tab4Content = new VerticalLayout();
    private final HorizontalLayout tab4ContentH = new HorizontalLayout();

    // Buttons
    private final Button btn_ReloadPoblationCl = new Button("Reload");
    private final Button btn_ReloadProductCl = new Button("Reload");
    private final Button filterButtonPoblation = new Button("Filtrar");
    private final Button filterButtonProduct = new Button("Filtrar");

    // Comboboxes
    private final ComboBox<String> filterComboBox = new ComboBox<>();
    private final ComboBox<String> productFilterComboBoxCl = new ComboBox<>();

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

        // Establecer el placeholder del ComboBox
        filterComboBox.setPlaceholder("Filter by MsCode");

        // Obtener todos los MsCode únicos de los datos
        List<String> uniqueMsCodes = service.Getdata().stream()
                .map(ndData::getMsCode)
                .distinct()
                .collect(Collectors.toList());

        // Añadir los MsCode únicos al ComboBox
        filterComboBox.setItems(uniqueMsCodes);

        // Crear un botón para aplicar el filtro
        filterButtonPoblation.addClickListener(e -> {
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
            } catch (Exception ex) {
                // Imprimir la traza de la excepción en caso de error
                ex.printStackTrace();
            }
        });

        // Añadir los objetos desde la API al grid
        List<ndData> ndData = service.GetDataMsC();
        gridMsC.setItems(ndData);
        // Añadir los objetos al layout

        LGMsC.add(gridMsC);
        LGMsC.setSizeFull();
        return LGMsC;
    }

    public VerticalLayout ProductsCV(GreetService service) throws Exception{

        VerticalLayout prCV = new VerticalLayout();
        // Creamos un Grid para mostrar los datos
        Grid<Products> gridprCV = new Grid<>(Products.class, false);
        Grid.Column<Products> IDMscColumn = gridprCV.addColumn(Products::getId).setHeader("ID");
        Grid.Column<Products> msCodeMscColumn = gridprCV.addColumn(Products::getName).setHeader("Name");
        Grid.Column<Products> yearMscColumn = gridprCV.addColumn(Products::getDescription).setHeader("Description");
        Grid.Column<Products> estCodeMscColumn = gridprCV.addColumn(Products::getStock).setHeader("Stock");
        Grid.Column<Products> estimateMscColumn = gridprCV.addColumn(Products::getExpiration_date).setHeader("Expiration Date");

        // Establecer el placeholder del ComboBox
        productFilterComboBoxCl.setPlaceholder("Filter by Product Name");

        // Obtener todos los MsCode únicos de los datos
        List<String> uniqueProductNames = service.GetProduct().stream()
                .map(Products::getName)
                .distinct()
                .collect(Collectors.toList());

        // Añadir los MsCode únicos al ComboBox
        productFilterComboBoxCl.setItems(uniqueProductNames);

        // Crear un botón para aplicar el filtro
        filterButtonProduct.addClickListener(e -> {
            // Obtener el valor seleccionado en el ComboBox
            String filterValue = productFilterComboBoxCl.getValue();
            List<Products> filteredProduct;
            try {
                // Filtrar los datos basándose en el valor seleccionado en el ComboBox
                filteredProduct = service.GetProduct().stream()
                        .filter(products -> products.getName().equals(filterValue))
                        .collect(Collectors.toList());
                // Actualizar el Grid con los datos filtrados
                gridprCV.setItems(filteredProduct);
            } catch (Exception ex) {
                // Imprimir la traza de la excepción en caso de error
                ex.printStackTrace();
            }
        });

        // Añadir los objetos desde la API al grid
        List<Products> products = service.GetProductOrder();
        gridprCV.setItems(products);
        // Añadir los objetos al layout

        prCV.add(gridprCV);
        prCV.setSizeFull();
        return prCV;
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
    public UserView(@Autowired GreetService service) throws Exception {

        // Creamos las pestañas
        Tab tab2 = new Tab("Población Objetivo");
        Tab tab4 = new Tab("Products");

        // Creamos el contenedor de las pestañas
        Tabs tabs = new Tabs(tab2, tab4);

        // Creamos el contenido de las pestañas
        tab2Content.setSizeFull();
        tab4Content.setSizeFull();

        //boton para recargar las tablas
        btn_ReloadPoblationCl.addClickListener(e -> {
            try {
                tab2ContentH.removeAll();
                tab2Content.removeAll();
                tab2ContentH.add(filterComboBox, filterButtonPoblation, btn_ReloadPoblationCl);
                tab2Content.add(tab2ContentH, GridMsC(service));

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        //boton para recargar las tablas
        btn_ReloadProductCl.addClickListener(e -> {
            try {
                tab4ContentH.removeAll();
                tab4Content.removeAll();
                tab4ContentH.add(productFilterComboBoxCl, filterButtonProduct, btn_ReloadProductCl);
                tab4Content.add(tab4ContentH, ProductsCV(service));

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // Creamos el contenido para cada pestaña
        tab2ContentH.add(filterComboBox, filterButtonPoblation, btn_ReloadPoblationCl);
        tab2Content.add(tab2ContentH, GridMsC(service));
        tab4ContentH.add(productFilterComboBoxCl, filterButtonProduct, btn_ReloadProductCl);
        tab4Content.add(tab4ContentH, ProductsCV(service));
        tab2Content.setVisible(true);
        tab4Content.setVisible(false);

        // Cambiamos el contenido visible según la pestaña seleccionada
        tabs.addSelectedChangeListener(event -> {
            if (tabs.getSelectedTab() == tab2) {
                tab2Content.setVisible(true);
                tab4Content.setVisible(false);
            }
            if (tabs.getSelectedTab() == tab4) {
                tab2Content.setVisible(false);
                tab4Content.setVisible(true);
            }
        });

        // Añadimos las pestañas
        add(tabs, tab2Content, tab4Content);
        setSizeFull();


    }
}
