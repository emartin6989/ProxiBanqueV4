package com.gtm.proxiv4.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestEffectuerVirement.class, TestListerComptesDecouvertConseiller.class,
		TestListerComptesDecouvertGerant.class })
public class AllTests {

}
