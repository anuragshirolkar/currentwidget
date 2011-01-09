/*
 *  Copyright (c) 2010-2011 Ran Manor
 *  
 *  This file is part of CurrentWidget.
 *    
 * 	CurrentWidget is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  CurrentWidget is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with CurrentWidget.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.manor.currentwidget.library;

import java.io.File;

import android.os.Build;

public class CurrentReaderFactory {
	
	// @@@ change all ICurentReader to static methods and return value here
	// (better than returning an object)
	static public ICurrentReader getCurrentReader() {
		
		File f = null;
		
		// htc desire hd / desire z?
		if (Build.MODEL.toLowerCase().contains("desire hd") ||
				Build.MODEL.toLowerCase().contains("desire z")) {
			f = new File("/sys/class/power_supply/battery/batt_current");
			if (f.exists())
				return new OneLineReader(f, false);
		}
		
		// sony ericsson xperia x1
		f = new File("/sys/devices/platform/i2c-adapter/i2c-0/0-0036/power_supply/ds2746-battery/current_now");
		if (f.exists())
			return new OneLineReader(f, false);
		
		// xdandroid
		/*if (Build.MODEL.equalsIgnoreCase("MSM")) {*/
			f = new File("/sys/devices/platform/i2c-adapter/i2c-0/0-0036/power_supply/battery/current_now");
			if (f.exists())
				return new OneLineReader(f, false);
		/*}*/
	
		// droid eris
		f = new File("/sys/class/power_supply/battery/smem_text");		
		if (f.exists())
			return new SMemTextReader();
		
		// some htc devices
		f = new File("/sys/class/power_supply/battery/batt_current");
		if (f.exists())
			return new OneLineReader(f, false);
		
		// nexus one
		f = new File("/sys/class/power_supply/battery/current_now");
		if (f.exists())
			return new OneLineReader(f, true);
		
		// samsung galaxy vibrant		
		f = new File("/sys/class/power_supply/battery/batt_chg_current");
		if (f.exists())
			return new OneLineReader(f, false);
		
		// sony ericsson x10
		f = new File("/sys/class/power_supply/battery/charger_current");
		if (f.exists())
			return new OneLineReader(f, false);
		
		return null;
	}
}