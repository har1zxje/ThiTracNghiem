package com.example.thitracnghiem.SQLHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import com.example.thitracnghiem.Models.CauHoiTracNghiem;
import com.example.thitracnghiem.Models.DeThi;
import com.example.thitracnghiem.Models.MonHoc;
import com.example.thitracnghiem.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ThiTracNghiem.db";
    private static final int DATABASE_VERSION = 1;

    // Tên bảng và các cột trong bảng MonHoc
    private static final String TABLE_MONHOC = "monhoc";
    private static final String COLUMN_MONHOC_ID = "id";
    private static final String COLUMN_MONHOC_TEN = "tenMonHoc";
    private static final String COLUMN_MONHOC_HINHANH = "hinhAnh";

    // Tên bảng và các cột trong bảng DeThi
    private static final String TABLE_DETHI = "dethi";
    private static final String COLUMN_DETHI_ID = "id";
    private static final String COLUMN_DETHI_MONHOC_ID = "idMonHoc";
    private static final String COLUMN_DETHI_SODETHI = "soDeThi";

    // Tên bảng và các cột trong bảng CauHoiTracNghiem
    private static final String TABLE_CAUHOI = "cauhoitracnghiem";
    private static final String COLUMN_CAUHOI_ID = "id";
    private static final String COLUMN_CAUHOI_DETHI_ID = "idDeThi";
    private static final String COLUMN_CAUHOI_NOIDUNG = "noiDungCauHoi";
    private static final String COLUMN_CAUHOI_PHUONGANA = "phuongAnA";
    private static final String COLUMN_CAUHOI_PHUONGANB = "phuongAnB";
    private static final String COLUMN_CAUHOI_PHUONGANC = "phuongAnC";
    private static final String COLUMN_CAUHOI_PHUONGAND = "phuongAnD";
    private static final String COLUMN_CAUHOI_DAPANDUNG = "dapAnDung";

    //Tên bảng và các cột trong bảng LichSuLamBai
    private static final String TABLE_LICHSULAMBAI = "lichsulambai";
    private static final String COLUMN_LICHSULAMBAI_ID = "id";
    private static final String COLUMN_LICHSULAMBAI_DETHI_ID = "idDeThi";
    private static final String COLUMN_LICHSULAMBAI_THOIGIANLAMBAI = "thoiGianLamBai";
    private static final String COLUMN_LICHSULAMBAI_DAPANDACHON = "dapAnDaChon";
    private static final String COLUMN_LICHSULAMBAI_DAPANDUNG = "dapAnDung";
    private static final String COLUMN_LICHSULAMBAI_CAUHOI = "cauHoi";
    private static final String COLUMN_LICHSULAMBAI_DAPAN_A = "dapAnA";
    private static final String COLUMN_LICHSULAMBAI_DAPAN_B = "dapAnB";
    private static final String COLUMN_LICHSULAMBAI_DAPAN_C = "dapAnC";
    private static final String COLUMN_LICHSULAMBAI_DAPAN_D = "dapAnD";
    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng MonHoc
        String createMonHocTable = "CREATE TABLE " + TABLE_MONHOC + "("
                + COLUMN_MONHOC_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_MONHOC_TEN + " TEXT,"
                + COLUMN_MONHOC_HINHANH + " TEXT)";
        db.execSQL(createMonHocTable);

        // Tạo bảng DeThi
        String createDeThiTable = "CREATE TABLE " + TABLE_DETHI + "("
                + COLUMN_DETHI_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_DETHI_MONHOC_ID + " INTEGER,"
                + COLUMN_DETHI_SODETHI + " INTEGER,"
                + "FOREIGN KEY(" + COLUMN_DETHI_MONHOC_ID + ") REFERENCES " + TABLE_MONHOC + "(" + COLUMN_MONHOC_ID + "))";
        db.execSQL(createDeThiTable);

        // Tạo bảng CauHoiTracNghiem
        String createCauHoiTable = "CREATE TABLE " + TABLE_CAUHOI + "("
                + COLUMN_CAUHOI_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_CAUHOI_DETHI_ID + " INTEGER,"
                + COLUMN_CAUHOI_NOIDUNG + " TEXT,"
                + COLUMN_CAUHOI_PHUONGANA + " TEXT,"
                + COLUMN_CAUHOI_PHUONGANB + " TEXT,"
                + COLUMN_CAUHOI_PHUONGANC + " TEXT,"
                + COLUMN_CAUHOI_PHUONGAND + " TEXT,"
                + COLUMN_CAUHOI_DAPANDUNG + " TEXT,"
                + "FOREIGN KEY(" + COLUMN_CAUHOI_DETHI_ID + ") REFERENCES " + TABLE_DETHI + "(" + COLUMN_DETHI_ID + "))";
        db.execSQL(createCauHoiTable);

        //Tạo bảng LichSu
        String createLichSuLamBaiTable = "CREATE TABLE " + TABLE_LICHSULAMBAI + "("
                + COLUMN_LICHSULAMBAI_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_LICHSULAMBAI_DETHI_ID + " INTEGER,"
                + COLUMN_LICHSULAMBAI_THOIGIANLAMBAI + " TEXT,"
                + COLUMN_LICHSULAMBAI_DAPANDACHON + " TEXT,"
                + COLUMN_LICHSULAMBAI_DAPANDUNG + " TEXT,"
                + COLUMN_LICHSULAMBAI_CAUHOI + " TEXT,"
                + COLUMN_LICHSULAMBAI_DAPAN_A + " TEXT,"
                + COLUMN_LICHSULAMBAI_DAPAN_B + " TEXT,"
                + COLUMN_LICHSULAMBAI_DAPAN_C + " TEXT,"
                + COLUMN_LICHSULAMBAI_DAPAN_D + " TEXT,"
                + "FOREIGN KEY(" + COLUMN_LICHSULAMBAI_DETHI_ID + ") REFERENCES " + TABLE_DETHI + "(" + COLUMN_DETHI_ID + "))";
        db.execSQL(createLichSuLamBaiTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAUHOI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DETHI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MONHOC);
        onCreate(db);
    }

    public void AddMonHoc() {
        deleteAllMonHoc();
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MONHOC_TEN, "Toán");
        values.put(COLUMN_MONHOC_HINHANH, R.drawable.montoan);
        database.insert(TABLE_MONHOC, null, values);
        values.put(COLUMN_MONHOC_TEN, "Vật lý");
        values.put(COLUMN_MONHOC_HINHANH, R.drawable.monly);
        database.insert(TABLE_MONHOC, null, values);
        values.put(COLUMN_MONHOC_TEN, "Hóa học");
        values.put(COLUMN_MONHOC_HINHANH,R.drawable.monhoa);
        database.insert(TABLE_MONHOC, null, values);
        values.put(COLUMN_MONHOC_TEN, "Tiếng anh");
        values.put(COLUMN_MONHOC_HINHANH,R.drawable.eng);
        database.insert(TABLE_MONHOC, null, values);

    }

    public List<MonHoc> getListMonHoc() {
        List<MonHoc> monHocList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String[] columns = {COLUMN_MONHOC_ID,COLUMN_MONHOC_TEN, COLUMN_MONHOC_HINHANH};
        Cursor cursor = database.query(TABLE_MONHOC, columns, null, null, null, null, null);
        int columnIndexIdMonHoc=cursor.getColumnIndex(COLUMN_MONHOC_ID);
        int columnIndexTenMonHoc = cursor.getColumnIndex(COLUMN_MONHOC_TEN);
        int columnIndexHinhAnh = cursor.getColumnIndex(COLUMN_MONHOC_HINHANH);
        if (columnIndexTenMonHoc != -1 && columnIndexHinhAnh != -1) {
            if (cursor.moveToFirst()) {
                do {
                    int idmonhoc=cursor.getInt(columnIndexIdMonHoc);
                    String tenMonHoc = cursor.getString(columnIndexTenMonHoc);
                    int hinhAnh = cursor.getInt(columnIndexHinhAnh);
                    MonHoc monHoc = new MonHoc(idmonhoc,tenMonHoc, hinhAnh);
                    monHocList.add(monHoc);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        database.close();
        return monHocList;
    }
    public void AddDeThi(int idMonHoc,int SoDeThi) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DETHI_MONHOC_ID, idMonHoc);
        values.put(COLUMN_DETHI_SODETHI, SoDeThi); // Example: Setting the "soDeThi" to 1
        long deThiId = database.insert(TABLE_DETHI, null, values);
        Log.e("Them thanh cong", "Them thanh cong - DeThi ID: " + deThiId);

        database.close();
    }
    public void AddDeThi() {
        deleteAllDeThi();
        for (int i =1; i <=getListMonHoc().size(); i++) {
            for (int j = 1; j <= 4; j++) {
                SQLiteDatabase database = getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(COLUMN_DETHI_MONHOC_ID, i);
                values.put(COLUMN_DETHI_SODETHI, j);
                database.insert(TABLE_DETHI, null, values);
                database.close();
            }
        }

    }
    public List<DeThi> getDeThiList(int idMonHoc) {
        List<DeThi> deThiList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String[] columns = {COLUMN_DETHI_ID, COLUMN_DETHI_SODETHI};
        String selection = COLUMN_DETHI_MONHOC_ID + " = ?";
        String[] selectionArgs = {String.valueOf(idMonHoc)};
        Cursor cursor = database.query(TABLE_DETHI, columns, selection, selectionArgs, null, null, null);
        int columnIndexDeThiId = cursor.getColumnIndex(COLUMN_DETHI_ID);
        int columnIndexSoDeThi = cursor.getColumnIndex(COLUMN_DETHI_SODETHI);
        if (columnIndexDeThiId != -1 && columnIndexSoDeThi != -1) {
            if (cursor.moveToFirst()) {
                do {
                    int deThiId = cursor.getInt(columnIndexDeThiId);
                    int soDeThi = cursor.getInt(columnIndexSoDeThi);
                    DeThi deThi = new DeThi(deThiId, idMonHoc, soDeThi);
                    deThiList.add(deThi);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        database.close();
        return deThiList;
    }

    public List<DeThi> getDeThiListByIds(List<Integer> deThiIds) {
        List<DeThi> deThiList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();

        String[] columns = {COLUMN_DETHI_ID, COLUMN_DETHI_MONHOC_ID, COLUMN_DETHI_SODETHI /* other DeThi columns you need */};
        String selection = COLUMN_DETHI_ID + " IN (" + TextUtils.join(",", Collections.nCopies(deThiIds.size(), "?")) + ")";
        String[] selectionArgs = new String[deThiIds.size()];
        for (int i = 0; i < deThiIds.size(); i++) {
            selectionArgs[i] = String.valueOf(deThiIds.get(i));
        }
        Cursor cursor = database.query(TABLE_DETHI, columns, selection, selectionArgs, null, null, null);
        int columnIndexDeThiId = cursor.getColumnIndex(COLUMN_DETHI_ID);
        int columnIndexMonHocId = cursor.getColumnIndex(COLUMN_DETHI_MONHOC_ID);
        int columnIndexSoDeThi = cursor.getColumnIndex(COLUMN_DETHI_SODETHI);
        if (columnIndexDeThiId != -1 && columnIndexMonHocId != -1 && columnIndexSoDeThi != -1) {
            if (cursor.moveToFirst()) {
                do {
                    int deThiId = cursor.getInt(columnIndexDeThiId);
                    int idMonHoc = cursor.getInt(columnIndexMonHocId);
                    int soDeThi = cursor.getInt(columnIndexSoDeThi);
                    DeThi deThi = new DeThi(deThiId, idMonHoc, soDeThi);
                    deThiList.add(deThi);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        database.close();

        return deThiList;
    }

    public void AddCauHoiTracNghiem(int idDeThi, String noiDungCauHoi, String phuongAnA, String phuongAnB, String phuongAnC, String phuongAnD, String dapAnDung) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CAUHOI_DETHI_ID, idDeThi);
        values.put(COLUMN_CAUHOI_NOIDUNG, noiDungCauHoi);
        values.put(COLUMN_CAUHOI_PHUONGANA, phuongAnA);
        values.put(COLUMN_CAUHOI_PHUONGANB, phuongAnB);
        values.put(COLUMN_CAUHOI_PHUONGANC, phuongAnC);
        values.put(COLUMN_CAUHOI_PHUONGAND, phuongAnD);
        values.put(COLUMN_CAUHOI_DAPANDUNG, dapAnDung);

        long cauHoiId = database.insert(TABLE_CAUHOI, null, values);
        Log.e("Thêm thành công", "Thêm thành công - Câu hỏi ID: " + cauHoiId);
        database.close();
    }

    public List<CauHoiTracNghiem> getCauHoiTracNghiemByDeThi(int idDeThi) {
        List<CauHoiTracNghiem> listCauHoi = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_CAUHOI + " WHERE " + COLUMN_CAUHOI_DETHI_ID + " = ?";

        Cursor cursor = database.rawQuery(selectQuery, new String[]{String.valueOf(idDeThi)});

        try {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_ID));
                    String noiDung = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_NOIDUNG));
                    String phuongAnA = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_PHUONGANA));
                    String phuongAnB = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_PHUONGANB));
                    String phuongAnC = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_PHUONGANC));
                    String phuongAnD = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_PHUONGAND));
                    String dapAnDung = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_DAPANDUNG));

                    CauHoiTracNghiem cauHoi = new CauHoiTracNghiem(id, idDeThi, noiDung, phuongAnA, phuongAnB, phuongAnC, phuongAnD, dapAnDung);
                    listCauHoi.add(cauHoi);
                    System.out.println("ID: " + cauHoi.getId());
                    System.out.println("ID Đề thi: " + cauHoi.getIdDeThi());
                    System.out.println("Nội dung: " + cauHoi.getNoiDungCauHoi());
                    System.out.println("Phương án A: " + cauHoi.getPhuongAnA());
                    System.out.println("Phương án B: " +cauHoi.getPhuongAnB());
                    System.out.println("Phương án C: " + cauHoi.getPhuongAnC());
                    System.out.println("Phương án D: " +cauHoi.getPhuongAnD());
                    System.out.println("Đáp án đúng: " + cauHoi.getDapAnDung());
                    System.out.println("--------------------------------");
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
            database.close();
        }

        return listCauHoi;
    }
    public void addLichSuLamBai(int idDeThi, String thoiGianLamBai, String dapAnDaChon, String dapAnDung, String cauHoi, String dapAnA, String dapAnB, String dapAnC, String dapAnD) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LICHSULAMBAI_DETHI_ID, idDeThi);
        values.put(COLUMN_LICHSULAMBAI_THOIGIANLAMBAI, thoiGianLamBai);
        values.put(COLUMN_LICHSULAMBAI_DAPANDACHON, dapAnDaChon);
        values.put(COLUMN_LICHSULAMBAI_DAPANDUNG, dapAnDung);
        values.put(COLUMN_LICHSULAMBAI_CAUHOI, cauHoi);
        values.put(COLUMN_LICHSULAMBAI_DAPAN_A, dapAnA);
        values.put(COLUMN_LICHSULAMBAI_DAPAN_B, dapAnB);
        values.put(COLUMN_LICHSULAMBAI_DAPAN_C, dapAnC);
        values.put(COLUMN_LICHSULAMBAI_DAPAN_D, dapAnD);

        long lichSuId = database.insert(TABLE_LICHSULAMBAI, null, values);
        Log.e("Thêm lịch sử", "Thêm lịch sử thành công - ID: " + lichSuId);
        database.close();
    }





    //Xóa lịch sử làm bài theo đề
    public void deleteLichSuById(int id){
        SQLiteDatabase database = getWritableDatabase();
        String selection = COLUMN_LICHSULAMBAI_DETHI_ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };
        int deletedRows = database.delete(TABLE_LICHSULAMBAI, selection, selectionArgs);
        if (deletedRows > 0) {
            System.out.println("Xóa thành công ");
        } else {
            System.out.println("Lỗi không có dữ liệu ");
        }
    }
    public void CauHoi(){
        deleteAllCauHoi();
        AddCauHoiTracNghiem(1,"Câu 1: Giải phương trình sau: 3x + 5 = 17.","A. x = 3","B.  x = 4","C.  x = 6","D. x = 4,5","B. x = 4");
        AddCauHoiTracNghiem(1,"Câu 2: Tính giá trị của biểu thức: 4² - 2 × 3 + 5.","A. 11","B. 15","C. 17","D. 19","D. 19");
        AddCauHoiTracNghiem(1,"Câu 3: Cho hình chữ nhật có chiều dài là 8 cm và chiều rộng là 5 cm. Tính diện tích hình chữ nhật đó.","A. 13 cm²","B. 30 cm²","C. 35 cm²","D. 40 cm².","C. 35 cm²");
        AddCauHoiTracNghiem(1,"Câu 4: Giải phương trình sau: 2x - 3 = 7x + 4.","A. x = -1","B. x = -2","C. x = 2","D. x = 3","A. x = -1");

        AddCauHoiTracNghiem(2,"Câu 1: Cho hàm số  . Tìm tất cả giá trị của   để hàm số đồng biến trên ?","A. m>0","B. m<=0","C. m>=0","D. m>1","A. m>0");
        AddCauHoiTracNghiem(2,"Câu 2: Trong các câu sau, câu nào không phải là mệnh đề?","A. Băng Cốc là thủ đô của Thái Lan","B. Buồn ngủ quá!","C. 8 là số lẻ.","D. Hình thoi có hai đường chéo vuông góc với nhau.","D. Hình thoi có hai đường chéo vuông góc với nhau.");
        AddCauHoiTracNghiem(2,"Câu 3: Với hai điểm phân biệt A, B ta có được bao nhiêu vectơ khác vectơ không có điểm đầu và điểm cuối là A hoặc B?","A. 1","B. 2","C. 2","D. 4.","D. 4.");
        AddCauHoiTracNghiem(2,"Câu 4: Cho tập hợpA={α;β;γ;λ}. Gọi X là tập hợp con của A và thỏa: β∈Xvà X có 3 phần từ. Số tập X là?","A. 3","B. 8","C. 13","D. Hình thoi có hai đường chéo vuông góc với nhau.","A. 3");

        AddCauHoiTracNghiem(3,"Câu 1: Giải phương trình sau: 3x + 5 = 17.","A. x = 3","B.  x = 4","C.  x = 6","D. x = 4,5","B. x = 4");
        AddCauHoiTracNghiem(3,"Câu 2: Tính giá trị của biểu thức: 4² - 2 × 3 + 5.","A. 11","B. 15","C. 17","D. 19","D. 19");
        AddCauHoiTracNghiem(3,"Câu 3: Cho hình chữ nhật có chiều dài là 8 cm và chiều rộng là 5 cm. Tính diện tích hình chữ nhật đó.","A. 13 cm²","B. 30 cm²","C. 35 cm²","D. 40 cm².","C. 35 cm²");
        AddCauHoiTracNghiem(3,"Câu 4: Giải phương trình sau: 2x - 3 = 7x + 4.","A. x = -1","B. x = -2","C. x = 2","D. x = 3","A. x = -1");

        AddCauHoiTracNghiem(4,"Câu 1: Cho hàm số  . Tìm tất cả giá trị của   để hàm số đồng biến trên ?","A. m>0","B. m<=0","C. m>=0","D. m>1","A. m>0");
        AddCauHoiTracNghiem(4,"Câu 2: Trong các câu sau, câu nào không phải là mệnh đề?","A. Băng Cốc là thủ đô của Thái Lan","B. Buồn ngủ quá!","C. 8 là số lẻ.","D. Hình thoi có hai đường chéo vuông góc với nhau.","D. Hình thoi có hai đường chéo vuông góc với nhau.");
        AddCauHoiTracNghiem(4,"Câu 3: Với hai điểm phân biệt A, B ta có được bao nhiêu vectơ khác vectơ không có điểm đầu và điểm cuối là A hoặc B?","A. 1","B. 2","C. 2","D. 4.","D. 4.");
        AddCauHoiTracNghiem(4,"Câu 4: Cho tập hợpA={α;β;γ;λ}. Gọi X là tập hợp con của A và thỏa: β∈Xvà X có 3 phần từ. Số tập X là?","A. 3","B. 8","C. 13","D. Hình thoi có hai đường chéo vuông góc với nhau.","A. 3");

        AddCauHoiTracNghiem(5,"Câu 1: Hãy chọn câu đúng?","A. Hệ quy chiếu bao gồm vật làm mốc, hệ toạ độ, mốc thời gian.","B. Hệ quy chiếu bao gồm hệ toạ độ, mốc thời gian và đồng hồ.","C. Hệ quy chiếu bao gồm vật làm mốc, mốc thời gian và đồng hồ.","D.Hệ quy chiếu bao gồm vật làm mốc, hệ toạ độ, mốc thời gian và đồng hồ.","D.Hệ quy chiếu bao gồm vật làm mốc, hệ toạ độ, mốc thời gian và đồng hồ.");
        AddCauHoiTracNghiem(5,"Câu 2: Hãy chỉ ra câu  sai? Chuyển động tròn đều là chuyển  động có các đặc điểm:","A. Tốc độ dài không đổi.","B. Quỹ đạo là đường tròn.","C. Tốc độ góc không đổi.  ","D. Vectơ gia tốc không đổi. ","D. Vectơ gia tốc không đổi.");
        AddCauHoiTracNghiem(5,"Câu 3: Với hai điểm phân biệt A, B ta có được bao nhiêu vectơ khác vectơ không có điểm đầu và điểm cuối là A hoặc B?","A. 1","B. 2","C. 2","D. 4.","D. 4.");
        AddCauHoiTracNghiem(5,"Câu 4: Tại cùng một vị trí xác định trên mặt đất và ở cùng độ cao thì :","A. Hai vật rơi với cùng vận tốc","B. Vận tốc của  hai vật không đổi.","C. Vận tốc của vật nặng nhỏ hơn vận tốc của vật nhẹ.","D. Vận tốc của vật nặng lớn hơn vận tốc của vật nhẹ.","A. Hai vật rơi với cùng vận tốc.");

        AddCauHoiTracNghiem(6,"Câu 1: Trong các  phát  biểu  dưới  đây, phát  biểu  nào  đúng ? tChuyển động cơ là: ","A. sự thay đổi vị trí của vật này so với vật khác theo thời gian.","B.sự thay đổi chiều của vật này so với vật khác theo thời gian.","C. sự thay đổi hướng của vật này so với vật khác theo thời gian.","D.sự thay đổi phương của vật này so với vật khác theo thời gian .","A. sự thay đổi vị trí của vật này so với vật khác theo thời gian.");
        AddCauHoiTracNghiem(6,"Câu 2: Hãy chỉ ra câu  sai? Chuyển động tròn đều là chuyển  động có các đặc điểm:","A. Tốc độ góc không đổi.  ","B. Quỹ đạo là đường tròn.","C. Vectơ gia tốc không đổi.  ","D. Tốc độ dài không đổi. ","C. Vectơ gia tốc không đổi.");
        AddCauHoiTracNghiem(6,"Câu 3: Đặc điểm nào dưới đây không phải là đặc điểm của vật chuyển động rơi tự do?","A. Chuyển động nhanh dần đều. ","B. Công thức tính vận tốc v = g. t2.","C. Tại một vị trí xác định và ở gần mặt đất, mọi vật rơi tự do như nhau.","D. Chuyển động theo phương thẳng đứng, chiều từ trên xuống dưới.","B. Công thức tính vận tốc v = g. t2.");
        AddCauHoiTracNghiem(6,"Câu 4: Khi ô tô đang chạy với vận tốc 10 m/s trên đoạn đường thẳng thì người lái xe hãm phanh và ô tô chuyển động chậm dần đều. Cho tới khi dừng hẳn lại thì  ô tô đã chạy  thêm được 50m. Nếu chọn chiều dương là chiều chuyển động của ô tô thì gia tốc của ô tô là:","A. a = - 0,5 m/s2. ","B. a = 0,5 m/s2.","C. a = 1 m/s2.","D. a = - 1 m/s2. ","D. a = - 1 m/s2.");

        AddCauHoiTracNghiem(7,"Câu 1: Lực tác dụng lên một vật có khối lượng 2 kg là 10 N. Tính gia tốc của vật đó.","A. 5 m/s².","B.  2 m/s².","C. 10 m/s².","D.  20 m/s².","A.  5 m/s².");
        AddCauHoiTracNghiem(7,"Câu 2: Điện trường được định nghĩa là: ","A.  Tích phân của mật độ dòng điện qua một bề mặt.","B.  Môđun của lực điện từ một điểm nào đó trong không gian.","C.  Quả cầu nhỏ nhất chứa điện tích dương.","D.  Lực tác dụng lên một điện tích dương đơn vị.","B.  Môđun của lực điện từ một điểm nào đó trong không gian.");
        AddCauHoiTracNghiem(7,"Câu 3: Ánh sáng trắng khi đi qua một prizma thủy tinh bị phân tán thành các màu sắc khác nhau do hiện tượng nào?","A. Phản xạ toàn phần.","B. Hiện tượng giao thoa.","C. Hiện tượng quang phổ.","D. Hiện tượng khúc xạ.","D. Hiện tượng khúc xạ.");
        AddCauHoiTracNghiem(7,"Câu 4: Định luật 3 của Newton nêu rằng:","A. Mọi hành động đều có phản ứng ngược lại.","B. Gia tốc của một vật phụ thuộc vào lực tác động lên nó.","C. Mọi vật liền kề đều tác động lực lên nhau.","D. Mỗi hành động đều có một hành động ngược lại tương ứng.","B. Gia tốc của một vật phụ thuộc vào lực tác động lên nó.");

        AddCauHoiTracNghiem(8,"Câu 1: Phương trình chuyển động của một chất điểm có dạng:x =2t + t ^2 (x; m; t; s).Vận tốc ban đầu của chất điểm: ","A. 1 m/s.","B.   2 m/s.","C.   10 m/s.","D.   12 m/s.","B.   2 m/s.");
        AddCauHoiTracNghiem(8,"Câu 2: Phương trình chuyển động thẳng đều của một chất điểm có dạng: x = 10t – 5. (x: km, t: h). Quãng đường đi được của chất điểm sau 2h là: ","A.  40 km.","B.  15 km.","C.  20 km.","D.  10 km..","C.  20 km.");
        AddCauHoiTracNghiem(8,"Câu 3: Phương trình chuyển động thẳng đều của một chất điểm có dạng: x = 4t – 10" + "(x: km, t: h). Tọa độ ban đầu của chất điểm là?","A. 4 km","B. - 6 km","C. 10 km","D. 14 km.","D. 14 km.");
        AddCauHoiTracNghiem(8,"Câu 4: Một vật nặng rơi từ độ cao 80m xuống mặt đất. Sau bao lâu vật chạm đất? Lấy g = 10 m/s2.","A. t = 1s.","B. t = 2s.","C. t = 3 s.","D. t = 4 s. ","C. t = 3 s.");

        AddCauHoiTracNghiem(9,"Câu 1: Nguyên tử có số proton là 6 và số neutron là 8 thuộc vào nguyên tố nào? ","A. Oxy (O) ","B. Carbon (C)","C. Nitơ (N)","D. Lưu huỳnh (S)","B. Carbon (C)");
        AddCauHoiTracNghiem(9,"Câu 2: Công thức hóa học của axit axetic là gì?","A. HCl","B. H2SO4","C. CH4 ","D. CH3COOH","D. CH3COOH");
        AddCauHoiTracNghiem(9,"Câu 3: Phản ứng nào sau đây là phản ứng trao đổi chất?","A. NaOH + HCl → NaCl + H2O","B. 2H2 + O2 → 2H2O.","C. CaCO3 → CaO + CO2","D. AgNO3 + NaCl → AgCl + NaNO3","A. NaOH + HCl → NaCl + H2O");
        AddCauHoiTracNghiem(9,"Câu 4: Nguyên tử của nguyên tố nào có cấu trúc electron là 2-8-7?","A. Fluor (F)","B. Nitơ (N)","C. Phốtpho (P)","D. Clor (Cl) ","C. Phốtpho (P)");

        AddCauHoiTracNghiem(10,"Câu 1: Chất nào sau đây có nhiệt độ nóng chảy cao nhất? ","A. MgO. ","B. Nước đá.","C. CO2 rắn. ","D. I2.","A. MgO.");
        AddCauHoiTracNghiem(10,"Câu 2: Dãy gồm các phân tử đều có liên kết ion là ","A. Cl2, Br2, I2, HCl.","B. HCl, H2S, NaCl, N2O.","C. Na2O, BaCl2, Al2O3, MgCl2. ","D. Na2SO4, CO2, BF3.","C. Na2O, BaCl2, Al2O3, MgCl2.");
        AddCauHoiTracNghiem(10,"Câu 3: Một orbital p có thể chứa tối đa bao nhiêu electron?","A. 2.","B. 6.","C. 10.","D. 14.","B. 6.");
        AddCauHoiTracNghiem(10,"Câu 4: Đồng vị là những nguyên tử của cùng một nguyên tố hóa học, chúng khác nhau về","A. tính chất hóa học.","B. số proton.","C. số neutron.","D. số electron. ","C. số neutron.");

        AddCauHoiTracNghiem(11,"Câu 1: Nguyên tử có số proton là 6 và số neutron là 8 thuộc vào nguyên tố nào? ","A. Oxy (O) ","B. Carbon (C)","C. Nitơ (N)","D. Lưu huỳnh (S)","B. Carbon (C)");
        AddCauHoiTracNghiem(11,"Câu 2: Công thức hóa học của axit axetic là gì?","A. HCl","B. H2SO4","C. CH4 ","D. CH3COOH","D. CH3COOH");
        AddCauHoiTracNghiem(11,"Câu 3: Phản ứng nào sau đây là phản ứng trao đổi chất?","A. NaOH + HCl → NaCl + H2O","B. 2H2 + O2 → 2H2O.","C. CaCO3 → CaO + CO2","D. AgNO3 + NaCl → AgCl + NaNO3","A. NaOH + HCl → NaCl + H2O");
        AddCauHoiTracNghiem(11,"Câu 4: Nguyên tử của nguyên tố nào có cấu trúc electron là 2-8-7?","A. Fluor (F)","B. Nitơ (N)","C. Phốtpho (P)","D. Clor (Cl) ","C. Phốtpho (P)");

        AddCauHoiTracNghiem(12,"Câu 1: Chất nào sau đây có nhiệt độ nóng chảy cao nhất? ","A. MgO. ","B. Nước đá.","C. CO2 rắn. ","D. I2.","A. MgO.");
        AddCauHoiTracNghiem(12,"Câu 2: Dãy gồm các phân tử đều có liên kết ion là ","A. Cl2, Br2, I2, HCl.","B. HCl, H2S, NaCl, N2O.","C. Na2O, BaCl2, Al2O3, MgCl2. ","D. Na2SO4, CO2, BF3.","C. Na2O, BaCl2, Al2O3, MgCl2.");
        AddCauHoiTracNghiem(12,"Câu 3: Một orbital p có thể chứa tối đa bao nhiêu electron?","A. 2.","B. 6.","C. 10.","D. 14.","B. 6.");
        AddCauHoiTracNghiem(12,"Câu 4: Đồng vị là những nguyên tử của cùng một nguyên tố hóa học, chúng khác nhau về","A. tính chất hóa học.","B. số proton.","C. số neutron.","D. số electron. ","C. số neutron.");

        AddCauHoiTracNghiem(13,"Câu 1: Which of the following is a synonym for \"exquisite\"? ","A. Beautiful ","B. Ugly","C. Ordinary ","D. Boring","A. Beautiful");
        AddCauHoiTracNghiem(13,"Câu 2: What is the capital city of Australia?","A. Sydney","B. Melbourne","C. Canberra ","D. Perth","C. Canberra");
        AddCauHoiTracNghiem(13,"Câu 3: Choose the correct sentence:","A. I goed to the store yesterday.","B. She is going to the party tomorrow.","C. They has finished their homework.","D.  We are go to the beach now.","B. She is going to the party tomorrow.");
        AddCauHoiTracNghiem(13,"Câu 4: What is the chemical symbol for gold?","A. Au","B. Ag","C. Fe","D. Pb ","A. Au");

        AddCauHoiTracNghiem(14,"Câu 1: Which of the following is a modal verb? ","A. Run ","B. Going","C. Can ","D. Jump","C. Can");
        AddCauHoiTracNghiem(14,"Câu 2: What is the past tense of the verb \"go\"?","A. Gone","B. Going","C. Goed ","D. Went\n","D. Went");
        AddCauHoiTracNghiem(14,"Câu 3: Choose the correct form of the verb to complete the sentence:\n" +
                "She ________ a book when I called her.","A. Read","B. Reads","C. Reading","D. Was reading","D. Was reading");
        AddCauHoiTracNghiem(14,"Câu 4: What is the comparative form of the adjective \"good\"?","A. Gooder","B. Goodest","C. Better","D. Best ","C. Better");

        AddCauHoiTracNghiem(15,"Câu 1: Which of the following is a synonym for \"exquisite\"? ","A. Beautiful ","B. Ugly","C. Ordinary ","D. Boring","A. Beautiful");
        AddCauHoiTracNghiem(15,"Câu 2: What is the capital city of Australia?","A. Sydney","B. Melbourne","C. Canberra ","D. Perth","C. Canberra");
        AddCauHoiTracNghiem(15,"Câu 3: Choose the correct sentence:","A. I goed to the store yesterday.","B. She is going to the party tomorrow.","C. They has finished their homework.","D.  We are go to the beach now.","B. She is going to the party tomorrow.");
        AddCauHoiTracNghiem(15,"Câu 4: What is the chemical symbol for gold?","A. Au","B. Ag","C. Fe","D. Pb ","A. Au");

        AddCauHoiTracNghiem(16,"Câu 1: Which of the following is a modal verb? ","A. Run ","B. Going","C. Can ","D. Jump","C. Can");
        AddCauHoiTracNghiem(16,"Câu 2: What is the past tense of the verb \"go\"?","A. Gone","B. Going","C. Goed ","D. Went\n","D. Went");
        AddCauHoiTracNghiem(16,"Câu 3: Choose the correct form of the verb to complete the sentence:\n" +
                "She ________ a book when I called her.","A. Read","B. Reads","C. Reading","D. Was reading","D. Was reading");
        AddCauHoiTracNghiem(16,"Câu 4: What is the comparative form of the adjective \"good\"?","A. Gooder","B. Goodest","C. Better","D. Best ","C. Better");




        getAllCauHoiTracNghiemList();
    }
    public void deleteAllMonHoc() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MONHOC, null, null);
        db.close();
    }
    public void deleteAllDeThi() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DETHI, null, null);
        db.close();
    }
    public void deleteAllCauHoi() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CAUHOI, null, null);
        db.close();
    }
    public void deleteAllLichSuLamBai(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LICHSULAMBAI, null, null);
        db.close();
    }
    public List<CauHoiTracNghiem> getAllCauHoiTracNghiemList() {
        List<CauHoiTracNghiem> listCauHoi = new ArrayList<>();

        try (SQLiteDatabase database = getReadableDatabase()) {
            String selectQuery = "SELECT * FROM " + TABLE_CAUHOI;
            Cursor cursor = database.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_ID));
                    int idDeThi = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_DETHI_ID));
                    String noiDung = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_NOIDUNG));
                    String phuongAnA = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_PHUONGANA));
                    String phuongAnB = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_PHUONGANB));
                    String phuongAnC = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_PHUONGANC));
                    String phuongAnD = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_PHUONGAND));
                    String dapAnDung = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAUHOI_DAPANDUNG));

                    CauHoiTracNghiem cauHoi = new CauHoiTracNghiem(id, idDeThi, noiDung, phuongAnA, phuongAnB, phuongAnC, phuongAnD, dapAnDung);
                    listCauHoi.add(cauHoi);

                    System.out.println("ID: " + cauHoi.getId());
                    System.out.println("ID Đề thi: " + cauHoi.getIdDeThi());
                    System.out.println("Nội dung: " + cauHoi.getNoiDungCauHoi());
                    System.out.println("Phương án A: " + cauHoi.getPhuongAnA());
                    System.out.println("Phương án B: " + cauHoi.getPhuongAnB());
                    System.out.println("Phương án C: " + cauHoi.getPhuongAnC());
                    System.out.println("Phương án D: " + cauHoi.getPhuongAnD());
                    System.out.println("Đáp án đúng: " + cauHoi.getDapAnDung());
                    System.out.println("--------------------------------");
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listCauHoi;
    }
    public void Reset(){
        AddMonHoc();
        AddDeThi();
        CauHoi();
    }
}