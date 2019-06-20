package cs3500.animator.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.OurModel;

// add a color wheel to edit shape color
public class CompositeView extends JFrame implements ControllableView {
  private GUIView guiView;
  private JButton pauseButton;
  private JButton rewindButton;
  private JScrollBar tickScroller;
  private JButton setLoop;
  private OurModel model;
  private JButton modifyShapes;
  private JButton modifyKeyFrames;
  private List<ActionListener> actionListeners;

  public CompositeView(int ticksPerSecond){
    super("Editor View");
    this.pauseButton = new JButton("Pause");
    this.pauseButton.setActionCommand("PauseEvent");
    this.rewindButton = new JButton("Rewind");
    this.rewindButton.setActionCommand("RewindEvent");
    this.tickScroller = new JScrollBar();
    this.setLoop = new JButton("Loop");
    this.setLoop.setActionCommand("LoopEvent");
    this.modifyShapes = new JButton();
    this.modifyKeyFrames = new JButton();
    this.guiView = new GUIView(ticksPerSecond);
    JPanel panel = new JPanel();
    panel.add(this.pauseButton);
    panel.add(this.rewindButton);
    panel.add(this.setLoop);
    this.add(panel, BorderLayout.PAGE_START);
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
  public void addActionListener(ActionListener actionListener){
    //this.actionListeners.add(actionListener);
    this.pauseButton.addActionListener(actionListener);
    this.rewindButton.addActionListener(actionListener);
    this.setLoop.addActionListener(actionListener);
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
    this.guiView.render(model);
  }
}
