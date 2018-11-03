
<u>Parking Lot - Automated Management</u>

Some assumptions are listed below 
- There is only one level in the parking lot, as command pattern for multilevel parking are not shared.
- As the input 'n' size is not  known. For fast retrieval of commands, extra data stucture like, <br>
    MultiMap<Color, RegId> <br>
    Map<RegId, SpotNo> <br>
  are maintained.<br>
  Otherwise, a simpler approach of iterating through the map, using sntrySet exists.

A Test class has been added to run various commands and validate the outputs.

