delimiter |

CREATE trigger
executePresc after insert
on prescription_execution 
for each row
begin
update prescription
SET completed = completed+1
WHERE prescription_id = new.prescription_id;
End;
|
delimiter ;