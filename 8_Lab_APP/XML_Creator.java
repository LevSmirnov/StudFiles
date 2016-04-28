package database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;
import org.jdom.Comment;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Lev
 */
public class XML_Creator {
Main.Activities   struct_Activities  = new Main.Activities();
Main.Kind_Activity   struct_Kind_Activity    = new Main.Kind_Activity();

    public XML_Creator(Main.Activities  struct_Activities1, Main.Kind_Activity struct_Kind_Activity1 ) {
        struct_Activities = struct_Activities1;
        struct_Kind_Activity = struct_Kind_Activity1;
    }

    public void XML_create() throws DataBase_Exception
    {
        DataBase_Interface database_int = new DataBase_Interface();

        // Создаем документ
        Document xmlDoc = new Document();
        // Создаем корневой элемент
        Element root = new Element("Activities");
        // Добавляем корневой элемент в документ
        xmlDoc.setRootElement(root);

        // Создаем элемент head и добавляем ему атрибут
        Element head1 = new Element("personalFile");
        head1.setAttribute("id", struct_Activities.id_file);

        StringTokenizer st = new StringTokenizer(struct_Activities.id_activity,"\n");
        StringTokenizer st1 = new StringTokenizer(struct_Activities.activity_date,"\n");
        StringTokenizer st2 = new StringTokenizer(struct_Activities.activity_date_off,"\n");

        while (st.hasMoreTokens())
        {
             String id_act = st.nextToken();
             String act_date = st1.nextToken();
             String act_date_off = st2.nextToken();

             Element id_activity = new Element("idActivity");
             id_activity.setAttribute("id", id_act );

             Element kind_activity = new Element("kindActivity");
             kind_activity.addContent(database_int.SelectData_Kind_Activity(struct_Kind_Activity, id_act));
             id_activity.addContent(kind_activity);

             Element activity_date = new Element("activity_date");
             activity_date.addContent(act_date);
             id_activity.addContent(activity_date);

             Element activity_date_off = new Element("activity_date_off");
             activity_date_off.addContent(act_date_off);
             id_activity.addContent(activity_date_off);

             head1.addContent(id_activity);
        }
        root.addContent(head1);
        try {
            // Получаем "красивый" формат для вывода XML
            // с переводами на новую строку и отступами
            Format fmt = Format.getPrettyFormat();

            // Выводим созданный XML как поток байт на стандартный
            // вывод и в файл, используя подготовленный формат
            XMLOutputter serializer = new XMLOutputter(fmt);
            serializer.output(xmlDoc, new FileOutputStream(new File("Heads.xml")));
            System.out.println("Файл Heads.xml успешно создан");
        }
        catch (IOException e) {
            System.err.println(e);
        }
   

    }
    public static void XML_create11()
    {
         // Создаем документ
        Document xmlDoc = new Document();
        // Создаем корневой элемент
        Element root = new Element("Head_list");
        // Добавляем корневой элемент в документ
        xmlDoc.setRootElement(root);

        // Создаем элемент head и добавляем ему атрибут
        Element head1 = new Element("head");
        head1.setAttribute("id", "1");
        // Добавляем комментарий в контент элемента head
        head1.addContent(new Comment("Head of IT department"));

        // Создаем элемент name и добавляем в него текст
        Element name1 = new Element("name");
        name1.addContent("Petrov P.");

        // Добавляем элемент name в элемент head
        head1.addContent(name1);

        // Создаем элемент department
        Element department1 = new Element("department");
        department1.addContent("IT");
        head1.addContent(department1);

        // Добавляем элемент head в корневой элемент
        root.addContent(head1);

        // Еще один элемент head создадим более кратко
        root.addContent(new Element("head").setAttribute("id","2")
                        .addContent(new Comment("Head of Sales"))
                            .addContent(new Element("name").addContent("Sidorov S."))
                            .addContent(new Element("department").addContent("Sales"))
                         );

        try {
            // Получаем "красивый" формат для вывода XML
            // с переводами на новую строку и отступами
            Format fmt = Format.getPrettyFormat();

            // Выводим созданный XML как поток байт на стандартный
            // вывод и в файл, используя подготовленный формат
            XMLOutputter serializer = new XMLOutputter(fmt);
            serializer.output(xmlDoc, System.out);
            serializer.output(xmlDoc, new FileOutputStream(new File("Heads.xml")));
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }
}
