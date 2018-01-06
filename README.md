
<u>Parking Lot - Automated Management</u>

Requirements as per the document shared.

Some assumptions are listed below because of some ambiguity in the document.
- There is only one level in the parking lot, as command pattern for multilevel parking are not shared.
- As the input 'n' size is not  known. For fast retrieval of commands, extra data stucture like, <br>
    MultiMap<Color, RegId> <br>
    Map<RegId, SpotNo> <br>
  are maintained.<br>
  Otherwise, a simpler approach of iterating through the map, using sntrySet exists.


A Test class has been added to run all the commands and validate the outputs.

