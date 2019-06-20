package cs3500.animator.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.OurModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

// add a color wheel to edit shape color
public class CompositeView extends JFrame implements ControllableView {
  private GUIView guiView;
  private JButton pauseButton;
  private JButton rewindButton;
  //private JScrollBar tickScroller;
  private JSpinner tickSpinner;
  private SpinnerNumberModel tickSpinnerModel;
  private JButton jumpTickButton;
  private JButton setLoop;
  private JButton modifyShapes;
  private JButton modifyKeyFrames;
  private List<ActionListener> actionListeners;

  public CompositeView(int ticksPerSecond){
    super("Editor View");
    this.pauseButton = new JButton("Pause");
    this.pauseButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        togglePause();
      }
    });
    this.rewindButton = new JButton("Rewind");
    this.rewindButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        toggleRewind();
      }
    });
    this.tickSpinner = new JSpinner();
    this.jumpTickButton = new JButton("Jump To Tick");
    this.jumpTickButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        setCurrentTick(tickSpinnerModel.getNumber().intValue());
      }
    });
    /*
    this.tickScroller = new JScrollBar(JScrollBar.HORIZONTAL);
    this.tickScroller.setMinimum(1);
    this.tickScroller.setValue(1);
     */
    this.setLoop = new JButton("Loop");
    this.setLoop.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        toggleLooping();
      }
    });
    this.modifyShapes = new JButton();
    this.modifyKeyFrames = new JButton();
    this.guiView = new GUIView(ticksPerSecond);
    JPanel panel = new JPanel();
    panel.add(this.pauseButton);
    panel.add(this.rewindButton);
    panel.add(this.setLoop);
    panel.add(this.jumpTickButton);
    panel.add(this.tickSpinner);
    this.add(panel, BorderLayout.PAGE_START);
    //this.add(tickScroller, BorderLayout.PAGE_END);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JScrollPane pane = new JScrollPane(this.guiView, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
     pane.setVisible(true);
    this.add(pane, BorderLayout.CENTER);
    /*
    this.pauseButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        togglePause();
      }
    });
    this.rewindButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        toggleRewind();
      }
    });
    this.setLoop.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        toggleLooping();
      }
    });
     */
    //this.actionListeners = new ArrayList<ActionListener>();
    this.setVisible(true);
  }

  @Override
  public void togglePause() {
    this.guiView.togglePause();
  }

  @Override
  public void toggleRewind() {
    this.guiView.toggleRewind();
  }

  @Override
  public void setSpeed(int ticksPerSecond) {
    this.guiView.setSpeed(ticksPerSecond);
  }

  @Override
  public void setCurrentTick(int tick) {
    this.guiView.setCurrentTick(tick);
  }

  @Override
  public void toggleLooping() {
    guiView.toggleLooping();
  }

  @Override
  public void render(IReadOnlyModel model) throws IllegalArgumentException {
    // add the guiview to this JFrame
    this.setSize(model.getCanvasWidth(), model.getCanvasHeight());
    this.tickSpinnerModel = new SpinnerNumberModel(1, 1, model.getMaximumTick(), 1);
    this.tickSpinner.setModel(this.tickSpinnerModel);
    this.guiView.render(model);
  }
}
