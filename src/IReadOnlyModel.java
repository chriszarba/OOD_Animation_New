import java.util.List;

public interface IReadOnlyModel {
  List<IShape> animate(int tick);
  List<String> getShapeNames();
  String getDescription();
}
