package com.dnikitin;

import com.dnikitin.service.UserServiceTest;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import java.io.PrintWriter;

public class TestLauncher {

    static void main() {
        var launcher = LauncherFactory.create();

        SummaryGeneratingListener summaryGeneratingListener = new SummaryGeneratingListener();
        //odpowiada za wyniki testow
        //launcher.registerTestExecutionListeners(summaryGeneratingListener); - mozna przekazac w taki sposob


        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder
                .request()
                //.selectors(DiscoverySelectors.selectClass(UserServiceTest.class))
                .selectors(DiscoverySelectors.selectPackage("com.dnikitin.service")) //lub package
                .build();
        launcher.execute(request, summaryGeneratingListener);

        try (PrintWriter writer = new PrintWriter(System.out)) {
            summaryGeneratingListener.getSummary().printTo(writer);
        }
    }
}
