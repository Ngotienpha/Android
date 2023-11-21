using SQLite;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Collections.ObjectModel;

namespace M_Hiker
{
    class MySQLiteDatabase
    {
        private SQLiteConnection dbConnection;
        public const string DB_FILENAME = "MyDB.db3";
        public const SQLiteOpenFlags FLAGS = SQLiteOpenFlags.ReadWrite |
                                             SQLiteOpenFlags.Create |
                                             SQLiteOpenFlags.SharedCache;

        public string dbPath = "";
        //public string currentState;

        public const string HIKE_TABLE = "Hike";
        public const string ID_COL = "ID";
        public const string NAME_COL = "Name";
        public const string LOCATION_COL = "Location";
        public const string DATE_COL = "Date";
        public const string LENGTH_COL = "Length";
        public const string LEVEL_COL = "Level";
        public const string PARKING_COL = "Parking Available";

        public MySQLiteDatabase()
        {
            init();
        }
        public void init()
        {
            dbPath = System.IO.Path.Combine(FileSystem.AppDataDirectory, DB_FILENAME);
            dbConnection = new SQLiteConnection(dbPath);
            dbConnection.CreateTable<Hike>();
        }
        public int insertHike (Hike h)
        {
            return dbConnection.Insert(h);
        }
        public ObservableCollection<Hike> loadHike()
        {
            return (new ObservableCollection<Hike>(dbConnection.Table<Hike>().ToList()));
        }
        public int updateHike(Hike h)
        {
            return dbConnection.Update(h);
        }

    }
}
