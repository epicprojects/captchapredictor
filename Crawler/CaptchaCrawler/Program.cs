using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;

namespace CaptchaCrawler
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main(String[] args)
        {
            //args[0] = "1";
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            //if (args[0] == "1")
            //{
           //     Application.Run(new ConvertToARFF(args[1]));
            //}
            //else
            //{
             //   Application.Run(new Form1());
            //}




            Application.Run(new ConvertToARFF());
        }
    }
}
