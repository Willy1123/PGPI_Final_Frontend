package org.vaadin.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
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
    private final VerticalLayout tab1Content = new VerticalLayout();
    private final HorizontalLayout tab1FilterReload = new HorizontalLayout();
    private final VerticalLayout tab2Content = new VerticalLayout();
    private final HorizontalLayout tab2FilteerReload = new HorizontalLayout();
    private final VerticalLayout tab3Content = new VerticalLayout();
    private final HorizontalLayout tab3Reload = new HorizontalLayout();

    // Buttons
    private final Button btn_ReloadPoblationCl = new Button("Reload");
    private final Button btn_ReloadProductCl = new Button("Reload");
    private final Button btn_ReloadPedidoCl = new Button("Reload");
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

    public VerticalLayout PedidosCV(GreetService service) throws Exception{

        VerticalLayout pedCV = new VerticalLayout();
        // Creamos un Grid para mostrar los datos
        Grid<Pedidos> gridPedCV = new Grid<>(Pedidos.class, false);
        Grid.Column<Pedidos> IdPedido = gridPedCV.addColumn(Pedidos::getId).setHeader("ID");
        Grid.Column<Pedidos> nameCampain = gridPedCV.addColumn(Pedidos::getNameCampain).setHeader("Campaing Name");
        Grid.Column<Pedidos> address = gridPedCV.addColumn(Pedidos::getAddress).setHeader("Address");
        Grid.Column<Pedidos> postal = gridPedCV.addColumn(Pedidos::getPostal).setHeader("Postal");
        Grid.Column<Pedidos> units = gridPedCV.addColumn(Pedidos::getUnits).setHeader("Units");


        // Añadir los objetos desde la API al grid
        //List<Pedidos> pedidos = service.GetProductOrder();
        //gridPedCV.setItems(pedidos);
        // Añadir los objetos al layout

        pedCV.add(gridPedCV);
        pedCV.setSizeFull();

        return  pedCV;
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
        Tab tab1 = new Tab("Población Objetivo");
        Tab tab2 = new Tab("Products");
        Tab tab3 = new Tab("Pedidos");

        // Creamos el contenedor de las pestañas
        Tabs tabs = new Tabs(tab1, tab2, tab3);

        // Creamos el contenido de las pestañas
        tab1Content.setSizeFull();
        tab2Content.setSizeFull();
        tab3Content.setSizeFull();

        //boton para recargar las tablas
        btn_ReloadPoblationCl.addClickListener(e -> {
            try {
                tab1FilterReload.removeAll();
                tab1Content.removeAll();
                tab1FilterReload.add(filterComboBox, filterButtonPoblation, btn_ReloadPoblationCl);
                tab1Content.add(tab1FilterReload, GridMsC(service));

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        //boton para recargar las tablas
        btn_ReloadProductCl.addClickListener(e -> {
            try {
                tab2FilteerReload.removeAll();
                tab2Content.removeAll();
                tab2FilteerReload.add(productFilterComboBoxCl, filterButtonProduct, btn_ReloadProductCl);
                tab2Content.add(tab2FilteerReload, ProductsCV(service));

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        //boton para recargar las tablas
        btn_ReloadPedidoCl.addClickListener(e -> {
            try {
                //tab2FilteerReload.removeAll();
                tab3Content.removeAll();
                //tab2FilteerReload.add(productFilterComboBoxCl, filterButtonProduct, btn_ReloadProductCl);
                tab3Content.add(btn_ReloadPedidoCl, PedidosCV(service));

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // Creamos el contenido para cada pestaña
        tab1FilterReload.add(filterComboBox, filterButtonPoblation, btn_ReloadPoblationCl);
        tab1Content.add(tab1FilterReload, GridMsC(service));
        tab2FilteerReload.add(productFilterComboBoxCl, filterButtonProduct, btn_ReloadProductCl);
        tab2Content.add(tab2FilteerReload, ProductsCV(service));
        tab3Content.add(btn_ReloadPedidoCl, PedidosCV(service));
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
