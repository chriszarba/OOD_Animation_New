# OOD_Animation
This is the animation project for Northeastern University's Object-Oriented Design class using a model-view-controller design.
Speciffically this is the Summer 1 2019 section.
This project will allow a user to generate an animation by creating shapes and motions for the shapes, and then displaying them.

# Design Decisions and Justification:
To associate shapes with a name, and motions with the shape,
we used HashMaps, due to the fact they have good performance characteristics for this (O(1)
insert/read, order unimportant). The names aren't stored in the motion or shape, because they are
only associated with the shape in this model. For example if a user wants to call the same shape
something else in a different class, that is possible in this implementation. Furthermore the
motions don't store a reference to the shape they refer to for the same reason, and because this
model handles the animation, which is the only thing both are required for. In this
implementation they are loosely coupled, allowing for greater flexibility in the future. The
motions are stored in a list that the name is associated with in the map. This is because a
single shape may have multiple associated motions, and the order of these motions does matter.
Therefore this allows us to keep the motions sorted by starting tick, using insertion sort (which
inserts in O(n) time, keeping the list sorted). This means they are already in chronological
order, which both makes it easier to find the correct motion for a given tick. Furthermore
motions and shapes are constructed internally, which means that the user does not have to worry
about their constructions. This is because they are implementation details, that is they are only
relevant to how the model fulfills its requirements, they are not essentially to fulfilling the
requirements. This means a User has less reliance on specific interfaces or classes, and allows
for easier refactoring of the models internal code without changing its external methods. It also
allows for better error checking, preventing a user from inputting a shape that may be invalid
for this model's assumptions. For example, our cs3500.animator.model.IShape interface does not put constraints on what
positions are valid. We made the assumption that the upper left was (0,0), and all coordinates
are positive numbers (as is standard in computer graphics). By constructing the shapes and
motions internally, this prevents the user from passing in a shape or motion with negative
coordinates. Also the coordinates are not locked to what part of the shape they represent. That
is something the view needs to worry about, not the model, and so the model makes no assumptions
about what the coordinates of a shape represent.

We also used Read-Only interfaces for both the model and shapes, which are extended to provide
mutable functions. This is due to the fact that only certain other classes should manipulate
shapes or the model, allowing someone to choose what classes are allowed to mutate the model. Our
model only returns the immutable shapes, as it does the calculations for the correct fields of
the shape using the given motions and tick, and so the shapes should not be changed outside of
the model. If they need to be a user can construct copies of the shapes, which further decouples
it from the model by allowing them more flexibility in class choice. Rather than throwing
exceptions for invalid shapes or motions, we simply return a boolean that describes the success
of the operation. This means the user does not have to use as many try-catch blocks (as throwing
exceptions is costly in performance) and allows a clear success or failure condition to proceed
on.

## Version 0.1
Created Model, Shape, and Motion interfaces and classes.

## Version 0.2
Refactor:
In OurView:
    HashMap -> LinkedHashMap
    Added getShapeMotions (from interface)
    Added getAllShapes() (from interface)
    Removed getTypeString();

In IModel interface:
    Added setters for Bounding x and y, and for Canvas Width/Height

In IReadOnlyModel interface:
    Removed getDescription() (moved to view);
    Added getShapeMotions(String name) - get motions associated with a shape
    Added getAllShapes()
    Added getters for Bounding x and y, and for Canvas Width/Height

In IReadOnlyShape:
    Added String getName(), added field to AbstractShape and subclasses

In OurModel:
    Added  IMotion getMotionAtTick(IReadOnlyShape shape, int tick),
           IReadOnlyShape getShapeAtTick(IReadOnlyShape shape, int tick),
           double tween(double start, double end, double startTick, double endTick, double tick),
           Color tweenColor(Color start, Color end, int startTick, int endTick, int tick),
           and added functionality for List<IReadOnlyShape> animate(int tick).
           Implemented new getters/setters for bounds from interface
           Allow negative positions
In OurMotion:
    Allow negative positions
In AbstractShape:
    Allow negative positions
