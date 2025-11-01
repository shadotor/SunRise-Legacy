package com.nightshadow.sunrisecreative.creative.coding.reader;

import com.nightshadow.sunrisecreative.creative.world.WorldStatement;
import com.nightshadow.sunrisecreative.creative.coding.logs.LogType;
import com.nightshadow.sunrisecreative.creative.coding.logs.Logs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.List;

import static com.nightshadow.sunrisecreative.Sunrise_Creative.getInstance;
import static com.nightshadow.sunrisecreative.api.utils.TextUtils.parseColor;
import static java.lang.Math.min;

public class Runner {
    long timeout;
    Reader r;
    Event e;
    boolean stop = false;
    int conditionLevel = 0;
    public Runner(Reader r) {
        this.r = r;
    }
    public Runner(Reader r, Event e) {
        timeout = 0;
        this.r = r;
        this.e = e;
    }
    public void run() {
        try {
            r.selector.setRunner(this);
            r.setColumn(0);
            for (int i = 0; i <= r.getLastColumn(); i++) {
                if (!isStopped() && r.getWorld().getStatement() == WorldStatement.PLAY) {
                    if (conditionLevel == 0) {
                        if (timeout > 0) {
                            Bukkit.getScheduler().runTaskLater(getInstance(), () -> {
                                if (r.columnExists(r.getLine(), r.column)) {
                                    ActionReader ar = r.getAction();
                                    ar.execute();
                                    r.setSelector(ar.getSelector());
                                }
                                timeout = 0;
                                r.nextColumn();
                            }, timeout);
                        } else {
                            if (r.columnExists(r.getLine(), r.column)) {
                                ActionReader ar = r.getAction();
                                ar.execute();
                                Selector outSelector = ar.getSelector();
                                outSelector.setRunner(this);
                                r.setSelector(outSelector);
                            }
                            r.nextColumn();
                        }
                    } else {
                        ActionReader ar = r.getAction();
                        if (ar.getAction().equals("CODE_CONDITION_END")) {
                            ar.execute();
                        }
                        r.nextColumn();
                    }
                }
            }
        } catch (Exception ex) {
            // Пишем в логи ошибку
            Logs errorLogs = new Logs(r.world);
            List<String> list = new ArrayList<>();
            errorLogs.addLine("=== Начало сообщения ошибки ===", LogType.ERROR);
            errorLogs.addLine("Код столкнулся с ошибкой!", LogType.ERROR);
            errorLogs.addLine("Ошибка: " + ex.getMessage(), LogType.ERROR);
            errorLogs.addLine("Место ошибки: " + "строка: " + r.getLine() + " столбец: " + r.getColumn(), LogType.ERROR );
            errorLogs.addLine("Если в коде допущена не ваша ошибка, отправьте информацию ниже: ", LogType.ERROR);

            errorLogs.addLine("Error message: " + ex.getMessage(), LogType.ERROR);
            errorLogs.addLine("Caused by action: " + r.getAction().action, LogType.ERROR);
            errorLogs.addLine("Coding location:" + r.getLine() + ":" + r.getColumn(), LogType.ERROR);
            errorLogs.addLine("World ID: " + r.getWorld().getID(), LogType.ERROR);
            // Стек
            for (int i = 0; i < min(ex.getStackTrace().length,10); i++) {
                String[] pack = ex.getStackTrace()[i].toString().split("//");
                String pkg;
                if (pack.length == 2) pkg = pack[1];
                else pkg = pack[0];
                errorLogs.addLine("at &f" + pkg, LogType.ERROR);
                list.add("&eat &f" + ex.getStackTrace()[i].getClass().getName());
            }
            errorLogs.addLine("===| Конец сообщения ошибки ===", LogType.ERROR);
            r.world.getWorld().getPlayers().forEach((Player p) -> {
                        // Выводим в чат ошибку
                        p.sendMessage(parseColor("&c================================================="));
                        p.sendMessage(parseColor("&cКод столкнулся с ошибкой!"));
                        p.sendMessage(parseColor("<hover:show_text:'" + String.join("\n", list) +"'>&6" + ex.getMessage()));
                        p.sendMessage(parseColor("&cДля подробной информации откройте логи(/log show)"));
                        p.sendMessage(parseColor("&c================================================="));
            });
        }
    }
    public void conditonBegin() {
        conditionLevel++;
    }
    public void conditionEnd() {
        if (conditionLevel > 0) {
            conditionLevel--;
        }
    }
    public boolean isStopped() {
        return this.stop;
    }
    public void stop() {
        this.stop = true;
    }
    public Event getEvent() {
        return e;
    }
    public Reader getReader() {
        return r;
    }
    public void sleep(long wait) {
        timeout = wait;
    }
}
