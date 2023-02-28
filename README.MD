#Learning Scalatest

##Styles

Represent only different manners of writing a test, there aren't differences on how the tests work<br>
just the way of writings changes.

### FunSuite

Most basic style of test, ideal for unit testing

### FlatSpec

BDD testing style

### FunSpec

BDD testing style based on Ruby's RSpec

### FunSpec

BDD testing style that gives total freedom on how the specification test should be writing.<br>
Since it doesn't follow patterns, it is indicated for teams experienced with BDD test that can<br>
decide by themselves how to write the tests.

### PropSpec

The AnyPropSpec style is perfect for teams that want to write tests exclusively in terms of property checks;<br>
also a good choice for writing the occasional test matrix when a different style trait is chosen<br>
as the main unit testing style.