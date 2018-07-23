package com.github.franckyi.cmpdl.tasks.validation;

import com.google.gson.JsonParser;
import javafx.concurrent.Task;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URL;
import java.util.Arrays;

public class ModpackURLValidationTask extends Task<ModpackURLValidationTask.ModpackURLValidationResult> {

    private String text;

    public ModpackURLValidationTask(String text) {
        this.text = text;
    }

    @Override
    protected ModpackURLValidationResult call() {
        try {
            URL url = new URL(text);
            if (!Arrays.asList("http", "https").contains(url.getProtocol())) {
                return error("This protocol is unsupported.");
            }
            Document root = Jsoup.parse(url, 10000);
            if ("minecraft.curseforge.com".equalsIgnoreCase(url.getHost()) || "www.feed-the-beast.com".equalsIgnoreCase(url.getHost())) {
                try {
                    if ("Modpacks".equalsIgnoreCase(root.select("h2.RootGameCategory").get(0).child(0).html())) {
                        return success(Integer.parseInt(root.select("ul.cf-details.project-details").get(0).child(0).child(1).html()));
                    } else {
                        return error("This project is not a modpack !");
                    }
                } catch (IndexOutOfBoundsException e) {
                    return error("This project is not a modpack !");
                }
            } else if ("www.curseforge.com".equalsIgnoreCase(url.getHost())) {
                try {
                    if ("Modpacks".equalsIgnoreCase(root.select("ul.b-breadcrumb-wrapper").get(0).child(2).child(0).child(0).html())) {
                        return success(new JsonParser().parse(root.select("a.button.button--icon.button--twitch.download-button").attr("data-action-value")).getAsJsonObject().get("ProjectID").getAsInt());
                    } else {
                        return error("This project is not a modpack !");
                    }
                } catch (IndexOutOfBoundsException e) {
                    return error("This project is not a modpack !");
                }
            } else {
                return error("This website is unsupported.");
            }
        } catch (HttpStatusException e) {
            return error("The URL returned an error " + e.getStatusCode() + ".");
        } catch (Throwable t) {
            return exception(t);
        }
    }

    private ModpackURLValidationResult exception(Throwable t) {
        return exception(t, t.getMessage());
    }

    private ModpackURLValidationResult exception(Throwable t, String str) {
        return error(t.getClass().getName(), str);
    }

    private ModpackURLValidationResult error(String str1, String str2) {
        return error(str1 + " : " + str2);
    }

    private ModpackURLValidationResult error(String str) {
        return new ModpackURLValidationResult(str);
    }

    private ModpackURLValidationResult success(int projectId) {
        return new ModpackURLValidationResult(projectId);
    }

    public static class ModpackURLValidationResult {

        private final boolean error;
        private final String errorMsg;
        private final int projectId;

        private ModpackURLValidationResult(String errorMsg) {
            this.error = true;
            this.errorMsg = errorMsg;
            this.projectId = -1;
        }

        private ModpackURLValidationResult(int projectId) {
            this.error = false;
            this.errorMsg = "";
            this.projectId = projectId;
        }

        public boolean isError() {
            return error;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public int getProjectId() {
            return projectId;
        }
    }
}
