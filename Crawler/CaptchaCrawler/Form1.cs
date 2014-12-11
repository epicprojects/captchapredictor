using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Net;
using System.IO;
using System.Diagnostics;
using System.Threading;

namespace CaptchaCrawler
{
    public partial class Form1 : Form
    {

        Boolean stop_flag = false;
        WebBrowser web;
        WebClient webClient = new WebClient();
        String src;
        Int32 count = 1;
        String last_image_path;
        Image cons;

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

          if(!Directory.Exists(Application.StartupPath+"\\Crawled Data"))
          {
              Directory.CreateDirectory(Application.StartupPath + "\\Crawled Data");
          }

          cons = pictureBox2.Image;
        }




        public void web_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        {

            foreach (HtmlElement img in web.Document.Images)
            {
                if (img.Id.ToString().ToLower() == textBox2.Text.ToLower())
                {
                    src = img.GetAttribute("src");
                    stat.Text = "Status: Crawled " + (count) + " of " + textBox1.Text;
                    this.Cursor = Cursors.AppStarting;
                    webClient.DownloadFile(src, Application.StartupPath + "\\Crawled Data\\"+(count++)+"_captcha.bmp");
                    last_image_path = Application.StartupPath + "\\Crawled Data\\" + (count - 1) + "_captcha.bmp";
                    pictureBox2.Image = Image.FromFile(last_image_path);
                }
            }    

            web.Dispose();




            if (count <= Convert.ToInt32(textBox1.Text) && stop_flag == false)
            {
                button1_Click(null, null);
            }
            else
            {
                count = 1;
                button1.Enabled = true;
                button2.Enabled = false;
                stat.Text = "Status: Crawling Completed";
                this.Cursor = Cursors.Arrow;
                pictureBox2.Image = cons;

                if (MessageBox.Show("Do you want to open the crawled CAPTCHA's?", "Notification", MessageBoxButtons.YesNo, MessageBoxIcon.Information) == System.Windows.Forms.DialogResult.Yes)
                {
                    Process.Start(Application.StartupPath + "\\Crawled Data");
                }

            }
            
        }


      




        private void button1_Click(object sender, EventArgs e)
        {
                stop_flag = false;
                this.Cursor = Cursors.AppStarting;
                web = new WebBrowser();
                web.DocumentCompleted += new WebBrowserDocumentCompletedEventHandler(web_DocumentCompleted);
                web.Navigate(new Uri(url.Text));
                button1.Enabled = false;
                button2.Enabled = true;
        }

       

      
        private void textBox1_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (e.KeyChar != Convert.ToChar(Keys.Back))
            {

                if ((e.KeyChar >= '0' & e.KeyChar <= '9'))
                {
                }
                else
                {
                    MessageBox.Show("Please Enter Number, Characters are invalid input", "Error!", MessageBoxButtons.OK, MessageBoxIcon.Hand);
                    e.Handled = true;
                }
            }
        }

        private void textBox3_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (e.KeyChar != Convert.ToChar(Keys.Back))
            {

                if ((e.KeyChar >= '0' & e.KeyChar <= '9'))
                {
                }
                else
                {
                    MessageBox.Show("Please Enter Number, Characters are invalid input", "Error!", MessageBoxButtons.OK, MessageBoxIcon.Hand);
                    e.Handled = true;
                }
            }
        }

        private void toolStripStatusLabel1_Click(object sender, EventArgs e)
        {

        }

        private void statusStrip1_ItemClicked(object sender, ToolStripItemClickedEventArgs e)
        {

        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void button2_Click(object sender, EventArgs e)
        {
            stop_flag = true;
        }

        private void button3_Click(object sender, EventArgs e)
        {
            this.WindowState = FormWindowState.Minimized;
        }

        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void stopToolStripMenuItem_Click(object sender, EventArgs e)
        {
            button2_Click(null, null);
        }

        private void crawlToolStripMenuItem_Click(object sender, EventArgs e)
        {
            button1_Click(null, null);
        }

        private void minimizeToolStripMenuItem_Click(object sender, EventArgs e)
        {
            button3_Click(null, null);
        }

        private void aboutToolStripMenuItem_Click(object sender, EventArgs e)
        {
            MessageBox.Show("This software is not for sale or distribution, created for study purpose in order to collect data for implementing machine learning based captcha cracking.", "About", MessageBoxButtons.OK, MessageBoxIcon.Information);
        }

        private void imagesARFFDatasetToolStripMenuItem_Click(object sender, EventArgs e)
        {
            ConvertToARFF caf = new ConvertToARFF();
            caf.Show();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            ConvertToARFF caf = new ConvertToARFF();
            caf.Show();
        }



      
        
    }
}
