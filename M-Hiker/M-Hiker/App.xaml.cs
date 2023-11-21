using System.Collections.ObjectModel;

namespace M_Hiker
{
    public partial class App : Application
    {
        public ObservableCollection<Hike> HikeList;
        public App()
        {
            InitializeComponent();

            MainPage = new AppShell();
        }
    }
}