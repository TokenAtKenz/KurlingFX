/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources.Help;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * found on the Internet
 * modified by Token AtKenz
 * @author unknown
 * @since 10 1 2016
 */
public class IniFile {
   private Pattern  _section  = Pattern.compile( "\\s*\\[([^]]*)\\]\\s*" );
   private Pattern  _keyValue = Pattern.compile( "\\s*([^=]*)=(.*)" );
   private Map< String,
      Map< String,
         String >>  _entries  = new HashMap<>();

   public IniFile( String path ) throws IOException {
      load( path );
   }

   public void load( String path ) throws IOException {
      try( BufferedReader br = new BufferedReader( new FileReader( path ))) {
         String line;
         String section = null;
         while(( line = br.readLine()) != null ) {
            Matcher m = _section.matcher( line );
            if( m.matches()) {
               section = m.group( 1 ).trim();
            }
            else if( section != null ) {
               m = _keyValue.matcher( line );
               if( m.matches()) {
                  String key   = m.group( 1 ).trim();
                  String value = m.group( 2 ).trim();
                  Map< String, String > kv = _entries.get( section );
                  if( kv == null ) {
                     _entries.put( section, kv = new HashMap<>());   
                  }
                  kv.put( key, value );
               }
            }
         }
      }
   }

   public String getString( String section, String key, String defaultvalue ) {
      Map< String, String > kv = _entries.get( section );
      if( kv == null ) {
         return defaultvalue;
      }
      String ret = kv.get(key);
      if(ret != null){return ret;}else {return defaultvalue;}
   }

   public int getInt( String section, String key, int defaultvalue ) {
      Map< String, String > kv = _entries.get( section );
      if( kv == null ) {
         return defaultvalue;
      }
      String ret = kv.get(key);
      if(ret != null)
          {return Integer.parseInt(ret );}
     else {return defaultvalue;}
      
   }

   public float getFloat( String section, String key, float defaultvalue ) {
      Map< String, String > kv = _entries.get( section );
      if( kv == null ) {
         return defaultvalue;
      }
      String ret = kv.get(key);
      if(ret != null)
          {return Float.parseFloat(ret );}
     else {return defaultvalue;}
   }

   public double getDouble( String section, String key, double defaultvalue ) {
      Map< String, String > kv = _entries.get( section );
      if( kv == null ) {
         return defaultvalue;
      }
      String ret = kv.get(key);
      if(ret != null)
          {return Double.parseDouble(ret );}
     else {return defaultvalue;}
   }
}