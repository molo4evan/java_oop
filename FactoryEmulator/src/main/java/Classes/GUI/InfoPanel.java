package Classes.GUI;

import Classes.Factory.AssemblyLine;
import Classes.Factory.AutoStorageController;
import Classes.Factory.Dealer;
import Classes.Factory.Factory;
import Classes.Factory.Suppliers.AccessorySupplier;
import Classes.Factory.Suppliers.Supplier;
import Classes.OS.Subscriber;
import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class InfoPanel extends JPanel{
    InfoPanel(Factory f){
        super(new GridLayout(1, 6, 0, 0));
        add(new SupplierInfo(f.getMotorSupplier(), "Motors", "Motor", f.getDelays().get(0)));
        add(new SupplierInfo(f.getBodySupplier(), "Bodies", "Body", f.getDelays().get(1)));
        add(new AccessorySuppliersInfo(f.getAccessorySuppliers(), f.getDelays().get(2)));
        add(new AssemblyLineInfo(f.getController().getLine()));
        add(new ControllerInfo(f.getController()));
        add(new DealerInfo(f.getDealers(), f.getDelays().get(3)));
    }
}

class SupplierInfo extends JPanel implements Subscriber {
    private Supplier s;
    private JLabel details;
    private String name;

    SupplierInfo(@NotNull Supplier s, String dname, String sname, int delay){
        super(new GridLayout(2, 1, 0, 0));
        this.s = s;
        s.addSub(this);
        name = dname;

        setBorder(BorderFactory.createTitledBorder( sname+ " supplier"));

        details = new JLabel(name + ": " + s.getTotal().toString());
        add(details);

        JSlider speed = new JSlider(500, 5000, delay);
        speed.setPaintLabels(true);
        speed.setPaintTicks(true);
        speed.setMinorTickSpacing(100);
        speed.setMajorTickSpacing(500);
        speed.setLabelTable(speed.createStandardLabels(1000));
        speed.addChangeListener(changeEvent -> {
            JSlider slider = (JSlider)changeEvent.getSource();
            if (slider.getValueIsAdjusting()) return;

            s.setDelay(slider.getValue());
        });

        add(speed);

    }

    @Override
    public void update() {
        details.setText(name + ": " + s.getTotal().toString());
        repaint();
    }
}

class AccessorySuppliersInfo extends JPanel implements Subscriber{
    private ArrayList<AccessorySupplier> sups;
    private JLabel accessories;

    AccessorySuppliersInfo(@NotNull ArrayList<AccessorySupplier> s, int delay){
        super(new GridLayout(3, 1, 0, 0));
        this.sups = s;
        for (AccessorySupplier as: sups) as.addSub(this);

        setBorder(BorderFactory.createTitledBorder("Accessory suppliers"));

        add(new JLabel("Amount: " + sups.size()));

        accessories = new JLabel("Accessoires: " + s.get(0).getTotal().toString());
        add(accessories);

        JSlider speed = new JSlider(500, 5000, delay);
        speed.setPaintLabels(true);
        speed.setPaintTicks(true);
        speed.setMinorTickSpacing(100);
        speed.setMajorTickSpacing(500);
        speed.setLabelTable(speed.createStandardLabels(1000));
        speed.addChangeListener(changeEvent -> {
            JSlider slider = (JSlider)changeEvent.getSource();
            if (slider.getValueIsAdjusting()) return;

            for (AccessorySupplier sup: sups) sup.setDelay(slider.getValue());
        });

        add(speed);
    }

    @Override
    public void update() {
        accessories.setText("Accessories: " + sups.get(0).getTotal().toString());
    }
}

class AssemblyLineInfo extends JPanel implements Subscriber{
    private AssemblyLine line;
    private JLabel cars;

    AssemblyLineInfo(@NotNull AssemblyLine l){
        super(new GridLayout(2, 1, 0, 0));
        line = l;
        line.addSub(this);

        setBorder(BorderFactory.createTitledBorder("Assembly line"));

        add(new JLabel("Workers: " + line.getWorkersAmount()));
        cars = new JLabel("Cars: " + line.getCarsAmount());
        add(cars);
    }

    @Override
    public void update() {
        cars.setText("Cars: " + line.getCarsAmount());
    }
}

class ControllerInfo extends JPanel implements Subscriber{
    private AutoStorageController ctrl;
    private JLabel cars;

    ControllerInfo(@NotNull AutoStorageController c){
        super(new GridLayout(3, 1, 0, 0));
        ctrl = c;
        ctrl.addSub(this);

        setBorder(BorderFactory.createTitledBorder("Autostorage controller"));

        add(new JLabel("Minimun: " + ctrl.getMinimalCapacity()));
        add(new JLabel("Order quantity: " + ctrl.getRequestSize()));
        cars = new JLabel("Cars: " + ctrl.getCarsAmount());
        add(cars);
    }

    @Override
    public void update() {
        cars.setText("Cars: " + ctrl.getCarsAmount());
    }
}

class DealerInfo extends JPanel implements Subscriber{
    private ArrayList<Dealer> dealers;
    private JLabel cars;

    DealerInfo(@NotNull ArrayList<Dealer> ds, int delay){
        super(new GridLayout(3, 1, 0, 0));
        dealers = ds;
        for (Dealer d: dealers) d.addSub(this);

        setBorder(BorderFactory.createTitledBorder("Dealers"));

        add(new JLabel("Amount: " + dealers.size()));
        cars = new JLabel("Cars: " + dealers.get(0).getTotal());
        add(cars);

        JSlider speed = new JSlider(500, 5000, delay);
        speed.setPaintLabels(true);
        speed.setPaintTicks(true);
        speed.setMinorTickSpacing(100);
        speed.setMajorTickSpacing(500);
        speed.setLabelTable(speed.createStandardLabels(1000));
        speed.addChangeListener(changeEvent -> {
            JSlider slider = (JSlider)changeEvent.getSource();
            if (slider.getValueIsAdjusting()) return;

            for (Dealer d: dealers) d.setDelay(slider.getValue());
        });

        add(speed);
    }

    @Override
    public void update() {
        cars.setText("Cars: " + dealers.get(0).getTotal());
    }
}