package co.touchlab.researchstack.glue.schedule;
import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by kgalligan on 11/27/15.
 */
public class ScheduleHelper
{
    public static Date nextSchedule(String cronString, Date lastExecution)
    {
        DateTime now = new DateTime(lastExecution);
        CronParser cronParser = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.UNIX));
        Cron cron = cronParser.parse(cronString);

        ExecutionTime executionTime = ExecutionTime.forCron(cron);
        DateTime nextExecution = executionTime.nextExecution(now);

        return nextExecution.toDate();
    }
}
