package com.advent;

import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class Exercise2 {

    public static int numReportsSave(List<List<Integer>> reports){

        return reports.stream()
                .map(report -> isReportSafe(report) ? 1 : 0)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static boolean isReportSafe(List<Integer> report){
        boolean safe = true;

        if (report.size() > 2){
            boolean increaseOp = report.get(0) < report.get(1);
            boolean decreaseOp = report.get(0) > report.get(1);
            safe = increaseOp || decreaseOp;

            int index = 1;
            while (index < report.size() && safe){
                if (increaseOp){
                    int increment = report.get(index) - report.get(index-1);
                    safe = increment>=1 && increment<=3;
                }
                else{
                    var decrement = report.get(index-1) - report.get(index);
                    safe = decrement>=1 && decrement<=3;
                }
                index++;
            }
        }

        return safe;
    }

    public static int numReportsSafeWithToleration(List<List<Integer>> reports){
        return reports.stream()
                .map(report -> isReportSafeWithToleration(report) ? 1 : 0)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static boolean isReportSafeWithToleration(List<Integer> report){
        var safe = isReportSafe(report);

        if (!safe) {
            int skip = 0;
            List<Integer> filteredReport;
            while (skip < report.size() && !safe) {
                filteredReport = new ArrayList<>(report);
                filteredReport.remove(skip);
                safe = isReportSafe(filteredReport);
                skip++;
            }
        }
        return safe;
    }
}
