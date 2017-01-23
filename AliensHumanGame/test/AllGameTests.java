import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import lifeform.*;
import environment.*;
import exceptions.TestExceptions;
import gameplay.TestSimpleTimer;
import recovery.*;
import weapon.*;
import ui.command.*;
import ui.*;

/**
 * Runs all of the tests in this project
 * @author David Reichard, Josh Varone (added additional classes)
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses
({
	TestLifeForm.class,
	TestCell.class,
	TestEnvironment.class,
	TestHuman.class,
	TestAlien.class,
	TestRecoveryNone.class,
	TestRecoveryLinear.class,
	TestRecoveryFractional.class,
	TestSimpleTimer.class,
	TestExceptions.class,
	TestPistol.class,
	TestGenericWeapon.class,
	TestChainGun.class,
	TestPlasmaCannon.class,
	TestStabilizer.class,
	TestPowerBooster.class,
	TestScope.class,
	TestTooManyAttachments.class,
	TestReload.class,
	TestTurn.class,
	TestMove.class,
	TestAttack.class,
	TestDrop.class,
	TestAcquire.class,
	TestUserInterfaceCommand.class,
	TestUserInterFaceLegend.class,
	TestUserInterFaceMap.class
})
public class AllGameTests 
{}