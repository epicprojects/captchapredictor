using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using Microsoft.VisualBasic;
using System.IO;
using System.Collections;

namespace CaptchaCrawler
{
    public partial class ConvertToARFF : Form
    {

        ImageToBin i2b = new ImageToBin();
        ArrayList segmntx = new ArrayList();
        ArrayList final_dataset = new ArrayList();
        public static String x;

        public int total;
        public string path;

        public ConvertToARFF(string path)
        {
            InitializeComponent();
            this.path = path;
            textBox1.Text = this.path;
        }


        public ConvertToARFF()
        {
            InitializeComponent();
          
        }
        
        private void button1_Click(object sender, EventArgs e)
        {
            folderBrowserDialog1.ShowDialog();
            textBox1.Text = folderBrowserDialog1.SelectedPath;

            Application.DoEvents();
            pictureBox1.Image = Image.FromFile(Directory.GetFiles(textBox1.Text).GetValue(0).ToString());
            pictureBox1.ImageLocation = Directory.GetFiles(textBox1.Text).GetValue(0).ToString();
            x = i2b.ConvertIMGtoBin(Directory.GetFiles(textBox1.Text).GetValue(0).ToString(), trackBar.Value);
            richTextBox1.Text = x;
            ColorTheKs();
            trackBar_Scroll(null, null);

        }

        private void ConvertToARFF_Load(object sender, EventArgs e)
        {

        }

        
        


        private void ColorTheKs()
        {
            for (int i = 0; i < richTextBox1.Text.Length; i++)
            {
                if (richTextBox1.Text[i] == '1')
                {
                    richTextBox1.SelectionStart = i;
                    richTextBox1.SelectionLength = 1;
                    richTextBox1.SelectionColor = Color.Red;
                    richTextBox1.SelectionBackColor = Color.Yellow;
                }
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            segmntx.Clear();
            final_dataset.Clear();

            foreach (String f_path in Directory.GetFiles(textBox1.Text))
            {
                Application.DoEvents();
                segmntx.Add(f_path);
            }


            Application.DoEvents();
            pictureBox1.Image = Image.FromFile(segmntx[0].ToString());
            pictureBox1.ImageLocation = segmntx[0].ToString();
            x = i2b.ConvertIMGtoBin(segmntx[0].ToString(), trackBar.Value);
            richTextBox1.Text = x;
            trackBar_Scroll(null, null);
            ColorTheKs();

             
            
            foreach(String s in richTextBox2.Lines)
            {
                Application.DoEvents();
                final_dataset.Add(s);
                
            }

            total = 0;
            foreach (String s in richTextBox1.Lines)
            {
                Application.DoEvents();
                total += s.Length;
            }

            
            final_dataset.Add("\n");
            final_dataset.Add("@relation captcha");
            final_dataset.Add("\n\n");

            int attrib_count = 1;
            for (int i = 0; i < total; i++)
            {
                Application.DoEvents();
                final_dataset.Add("@attribute px" + attrib_count + " real");
                attrib_count++;
            }

            final_dataset.Add("@attribute alphabet {a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z}");


            final_dataset.Add("\n\n");

            final_dataset.Add("@data");

       
            foreach (String segment in segmntx)
            {
                Application.DoEvents();
                pictureBox1.Image = Image.FromFile(segment);
                pictureBox1.ImageLocation = segment;
                x = i2b.ConvertIMGtoBin(segment, trackBar.Value);
                richTextBox1.Text = x;
                trackBar_Scroll(null, null);
                ColorTheKs();

            above:
                String s = Interaction.InputBox("Please Enter the Character", "Enter the Shown Character");


                try
                {
                    if (s != "")
                    {
                        if (Char.IsLetterOrDigit(Convert.ToChar(s)) || s == "?")
                        {
                            String tuple = "";
                            foreach (String line in richTextBox1.Lines)
                            {
                                foreach (Char c in line)
                                {
                                    tuple += c.ToString()+",";
                                }
                            }

                            tuple += s;

                            final_dataset.Add(tuple);

                           // MessageBox.Show("Tuple Ready");
                        }
                        else
                        {
                            // MessageBox.Show("Invalid Input, Try again");
                            goto above;
                        }
                    }
                    else
                    {
                        goto bhair;
                    }

                }
                catch
                {
                    goto above;
                }
            }

            pictureBox1.Image = null;
            richTextBox1.Clear();
            
            if (MessageBox.Show("Do You Want To Write The DataSet To An ARFF File?", "Do You?", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == System.Windows.Forms.DialogResult.Yes)
            {
              saveFileDialog1.ShowDialog();
              String path;

                if(saveFileDialog1.FileName != "")
                {
                   path =  saveFileDialog1.FileName;
                     if (saveFileDialog1.CheckPathExists)
                      {
                    SaveArraytoFile(final_dataset, path);
                      }
                }
                else
                {
                   MessageBox.Show("Invalid File Name");
                }
               
               
            }

            final_dataset.Clear();
            segmntx.Clear();

        bhair:
            ;


        }


        public static void SaveArraytoFile(System.Collections.ArrayList ar, string fileName)
        {
            using (System.IO.StreamWriter sw = new StreamWriter(fileName))
            {
                foreach (var item in ar)
                {
                    sw.WriteLine(item);
                }
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
           // ToImage(
        }

        private void trackBar_Scroll(object sender, EventArgs e)
        {
            textBox2.Text = "";
            int i = 0;
            String[] splitd = richTextBox1.Text.Split('\n');
            for (i=0; i < richTextBox1.Text.Length; i++)
            {
                if (richTextBox1.Text[i] == '\n')
                {
                    break;
                }
            }

            textBox2.Text = (i+1) + " x " + (splitd.Length - 1);
            splitd = null;

            x = i2b.ConvertIMGtoBin(pictureBox1.ImageLocation, trackBar.Value);
            richTextBox1.Text = x;
            ColorTheKs();
          

        }

        private void webBrowser1_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        {

        }

        private void label6_Click(object sender, EventArgs e)
        {

        }

        private void richTextBox2_TextChanged(object sender, EventArgs e)
        {

        }
    }
}
