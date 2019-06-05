import java.util.List;

public interface IReadOnlyModel {
  List<IShape> getShapesAtTick(int tick);
  List<String> getShapeNames();
  String getDescription();
}
