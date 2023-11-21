using System.Collections.ObjectModel;

namespace M_Hiker
{
    public partial class MainPage : ContentPage
    {
        int count = 0;
        App thisApp = Microsoft.Maui.Controls.Application.Current as App;
        private MySQLiteDatabase myDB;

        public MainPage()
        {
            InitializeComponent();
            this.swtParking.Items.Add("Yes");
            this.swtParking.Items.Add("No");
            this.swtLevel.Items.Add("Easy");
            this.swtLevel.Items.Add("Normal");
            this.swtLevel.Items.Add("Hard");

            thisApp.HikeList = new ObservableCollection<Hike>();
            myDB = new MySQLiteDatabase();
        }

        private void btnView_Clicked(object sender, EventArgs e)
        {
            Navigation.PushModalAsync(new HikeList(), true);
        }

        private void btnLoad_Hike_Clicked(object Sender, EventArgs e)
        {
            thisApp.HikeList = myDB.loadHike();
        }

        private async void btnSubmit_Clicked(object sender, EventArgs e)
        {
            var response = await DisplayAlert("Confirmation", "Do you want to save?", "OK", "Cancel");
            if (response)
            {
                Hike h = new Hike(count++, this.txtName.Text,
                                           this.txtLocation.Text,
                                           this.dtpDateofHike.Date,
                                           this.txtLength.Text,
                                           this.swtLevel.SelectedItem.ToString(),
                                           this.swtParking.SelectedItem.ToString()); ;
                thisApp.HikeList.Add(h);
                myDB.insertHike(h);
                await DisplayAlert("Information", "Information saved", "OK");
            }
        }
    }
}