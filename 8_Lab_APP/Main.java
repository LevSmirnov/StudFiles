
package database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;






public class Main  {

        public static void load_to_database(JSON_Parser js)
        {
            DataBase_Interface database_int = new DataBase_Interface();
            try {
                if (!js.JSON_parse()) return;
                database_int.initDriver();
                //database_int.insertData_Father_info(js.struct_Father_info);
                //database_int.insertData_Mother_info(js.struct_Mother_info);
                //database_int.insertData_Recruit(js.struct_Recruit);
                database_int.insertData_Personal_file(js.struct_Pers_File);
            } catch (DataBase_Exception ex) {
                System.out.println("Ошибка при добавлении данных");
                return;
            }
            System.out.println("Данные из .json успешно добавлены");
        }
        public static boolean load_from_database(Activities struct_Activities, String id_file) throws DataBase_Exception
        {
            DataBase_Interface database_int = new DataBase_Interface();
            if (!database_int.SelectData_Activities(struct_Activities, id_file))
            {
                System.out.println("По запросу с данным id результатов нет");
                return false;
            }
            return true;
        }
        public static class Personal_File
        {
            public String id_file;
            public String id_recruit;
            public String registr_date;
        };
        public static class Recruit
        {
            public String id_recruit;
            public String name;
            public String birth_date;
            public String registr_place;
            public String id_mother;
            public String id_father;
            public String id_academic;
        };
        public static class Father_info
        {
            public String id_father;
            public String name;
            public String birth_date;
            public String registr_place;
        };
        public static class Mother_info
        {
            public String id_mother;
            public String name;
            public String birth_date;
            public String registr_place;
        };
        public static class Activities
        {
            public String id_file;
            public String id_activity;
            public String activity_date;
            public String activity_date_off;
        };
        public static class Kind_Activity
        {
            public String id_activity;
            public String kind_activity;
        };
    public static void main(String[] args) throws DataBase_Exception {
        // объявление структур для заполнения парсером
        Personal_File   struct_Pers_File        = new Personal_File();
        Recruit         struct_Recruit          = new Recruit();
        Father_info     struct_Father_info      = new Father_info();
        Mother_info     struct_Mother_info      = new Mother_info();
        Activities      struct_Activities       = new Activities();
        Kind_Activity   struct_Kind_Activity    = new Kind_Activity();
        // ---

        // для чтения входного потока
        BufferedReader in = null;
        // ---
        String fuser = ""; // входные данные от пользователя
        

        //------------------------ КОНЕЦ ОПРЕДЕЛЕНИЙ ------------------------


        in = new BufferedReader(new InputStreamReader(System.in));
        

        System.out.println("Добро пожаловать --Database_Worker--");
        System.out.println("Поддерживаемые команды:\n\t\t\t--save <id_дела> - сохранить данные о активностях призывника в xml");
        System.out.println("\t\t\t--load <имя_файла.json> - загрузить данные из файла .json в базу данных");
        System.out.println("\t\t\t--exit - выход из программы");
        System.out.println("-----------------------------------------------------------------------");
        System.out.print("cmd>");

        try {
            while ((fuser = in.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(fuser," ");
                String cmd = st.nextToken();
                if (cmd.equals("load"))
                {
                    if (st.hasMoreTokens())
                    {
                        JSON_Parser js = new JSON_Parser(struct_Pers_File, struct_Recruit,
                        struct_Father_info, struct_Mother_info, st.nextToken());
                        load_to_database(js);
                    }
                    else System.out.println("Неправильнй ввод");
                }
                else if (cmd.equals("save"))
                {
                    if (st.hasMoreTokens())
                    {
                        try
                        {
                            String id_file = st.nextToken();
                            Integer.parseInt(id_file);
                            if (load_from_database(struct_Activities, id_file))
                            {
                                XML_Creator xml = new XML_Creator(struct_Activities, struct_Kind_Activity); 
                                xml.XML_create();
                            }
                            
                            
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("Неправильный ввод id-личного дела");
                        }
                        



                        
                    }
                    else System.out.println("Неправильнй ввод");
                }
                else if(fuser.equalsIgnoreCase("exit")) break;

                System.out.print("cmd>");
            }
        } catch (IOException ex) {
            System.out.println("Ошибка входного потока ");
            System.exit(-1);
        }


    }

}
