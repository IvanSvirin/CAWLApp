/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cawlapp.cawlapp.data.cache;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Helper class to do operations on regular files/directories.
 */
@Singleton
public class PreferencesManager {

  @Inject
  public PreferencesManager() {}

  /**
   * Write a value to a user preferences file.
   *
   * @param context {@link Context} to retrieve android user preferences.
   * @param preferenceFileName A file name reprensenting where data will be written to.
   * @param key A string for the key that will be used to retrieve the value in the future.
   * @param value A long representing the value to be inserted.
   */
  public void writeToPreferences(Context context, String preferenceFileName, String key,
                                 long value) {

    SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName,
        Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putLong(key, value);
    editor.apply();
  }

  /**
   * Get a value from a user preferences file.
   *
   * @param context {@link Context} to retrieve android user preferences.
   * @param preferenceFileName A file name representing where data will be get from.
   * @param key A key that will be used to retrieve the value from the preference file.
   * @return A long representing the value retrieved from the preferences file.
   */
  public long getFromPreferences(Context context, String preferenceFileName, String key) {
    SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName,
        Context.MODE_PRIVATE);
    return sharedPreferences.getLong(key, 0);
  }
}
