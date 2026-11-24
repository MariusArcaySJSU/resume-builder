package App.View;

import java.time.LocalDate;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

/** Keeps a start/end DatePicker pair in chronological order. */
public final class DateRangeFX {

    private DateRangeFX() {
    }

    public static void enforce(DatePicker startDatePicker, DatePicker endDatePicker) {
        startDatePicker.valueProperty().addListener((observable, oldDate, newDate) -> {
            LocalDate endDate = endDatePicker.getValue();
            if (newDate != null && endDate != null && endDate.isBefore(newDate)) {
                endDatePicker.setValue(newDate);
            }
            refreshDayCells(startDatePicker, endDatePicker);
        });

        endDatePicker.valueProperty().addListener((observable, oldDate, newDate) -> {
            LocalDate startDate = startDatePicker.getValue();
            if (startDate != null && newDate != null && newDate.isBefore(startDate)) {
                endDatePicker.setValue(startDate);
            }
            refreshDayCells(startDatePicker, endDatePicker);
        });

        refreshDayCells(startDatePicker, endDatePicker);
    }

    private static void refreshDayCells(DatePicker startDatePicker, DatePicker endDatePicker) {
        startDatePicker.setDayCellFactory(picker -> {
            DateCell cell = new DateCell();
            cell.itemProperty().addListener((observable, oldDate, date) -> {
                LocalDate endDate = endDatePicker.getValue();
                cell.setDisable(date != null && endDate != null && date.isAfter(endDate));
            });
            return cell;
        });

        endDatePicker.setDayCellFactory(picker -> {
            DateCell cell = new DateCell();
            cell.itemProperty().addListener((observable, oldDate, date) -> {
                LocalDate startDate = startDatePicker.getValue();
                cell.setDisable(date != null && startDate != null && date.isBefore(startDate));
            });
            return cell;
        });
    }
}
