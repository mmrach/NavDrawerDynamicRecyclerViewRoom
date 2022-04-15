package com.amm.navdrawerdynamicrecyclerviewroom.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.amm.navdrawerdynamicrecyclerviewroom.Ingrediente;
import com.amm.navdrawerdynamicrecyclerviewroom.IngredienteDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Ingrediente.class}, version = 1, exportSchema = false)
abstract public class AppRoomDatabase extends RoomDatabase {
    public abstract IngredienteDao ingredienteDao();

    private static volatile AppRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 1;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppRoomDatabase.class, "ingredientes")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                IngredienteDao dao = INSTANCE.ingredienteDao();
                //dao.deleteAll();

                Ingrediente ingrediente = new Ingrediente("Leche");
                dao.insert(ingrediente);
                ingrediente = new Ingrediente("Canela");
                dao.insert(ingrediente);
            });
        }
    };
}
