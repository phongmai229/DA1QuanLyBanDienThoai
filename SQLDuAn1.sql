USE [master]
GO
/****** Object:  Database [DA1]    Script Date: 4/20/2024 10:30:10 AM ******/
CREATE DATABASE [DA1]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'DA1', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\DA1.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'DA1_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\DA1_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [DA1] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [DA1].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [DA1] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [DA1] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [DA1] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [DA1] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [DA1] SET ARITHABORT OFF 
GO
ALTER DATABASE [DA1] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [DA1] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [DA1] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [DA1] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [DA1] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [DA1] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [DA1] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [DA1] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [DA1] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [DA1] SET  ENABLE_BROKER 
GO
ALTER DATABASE [DA1] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [DA1] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [DA1] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [DA1] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [DA1] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [DA1] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [DA1] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [DA1] SET RECOVERY FULL 
GO
ALTER DATABASE [DA1] SET  MULTI_USER 
GO
ALTER DATABASE [DA1] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [DA1] SET DB_CHAINING OFF 
GO
ALTER DATABASE [DA1] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [DA1] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [DA1] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [DA1] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'DA1', N'ON'
GO
ALTER DATABASE [DA1] SET QUERY_STORE = OFF
GO
USE [DA1]
GO
/****** Object:  UserDefinedFunction [dbo].[F_GetTextTrangThai]    Script Date: 4/20/2024 10:30:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE function [dbo].[F_GetTextTrangThai] (@ngayBatDau date,@ngayKetThuc date)
returns int
as begin
	if(DATEDIFF(DAY,GETDATE(),@ngayBatDau) <= 0 and DATEDIFF(DAY,GETDATE(),@ngayKetThuc) >= 0) begin return 0 end
	else if(DATEDIFF(DAY,GETDATE(),@ngayBatDau) > 0)  begin return 1 end
	else if(DATEDIFF(DAY,GETDATE(),@ngayKetThuc) < 0) begin return 2 end
	return 3
end
GO
/****** Object:  Table [dbo].[CAMERA]    Script Date: 4/20/2024 10:30:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CAMERA](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[ID_Camera] [int] NOT NULL,
	[ID_San_Pham] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CAMERAALL]    Script Date: 4/20/2024 10:30:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CAMERAALL](
	[ID_Camera] [int] IDENTITY(1,1) NOT NULL,
	[Loai_Camera] [int] NOT NULL,
	[So_Luong] [int] NOT NULL,
	[Thong_So_Ky_Thuat] [int] NOT NULL,
	[Trang_Thai] [int] NOT NULL,
	[Ngay_Sua] [date] NULL,
	[Ngay_Tao] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_Camera] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CHIP]    Script Date: 4/20/2024 10:30:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CHIP](
	[ID_Chip] [int] IDENTITY(1,1) NOT NULL,
	[Ten_Chip] [varchar](50) NOT NULL,
	[Trang_Thai] [int] NOT NULL,
	[Ngay_Sua] [date] NULL,
	[Ngay_Tao] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_Chip] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CONGSAC]    Script Date: 4/20/2024 10:30:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CONGSAC](
	[ID_Cong_Sac] [int] IDENTITY(1,1) NOT NULL,
	[Ten_Cong_Sac] [varchar](20) NOT NULL,
	[Cong_Nghe] [int] NOT NULL,
	[Trang_Thai] [int] NOT NULL,
	[Ngay_Sua] [date] NULL,
	[Ngay_Tao] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_Cong_Sac] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DONG]    Script Date: 4/20/2024 10:30:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DONG](
	[ID_Dong] [int] IDENTITY(1,1) NOT NULL,
	[Trang_Thai] [int] NOT NULL,
	[Ten_Dong] [varchar](50) NOT NULL,
	[Ngay_Sua] [date] NULL,
	[Ngay_Tao] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_Dong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GIOHANGCT]    Script Date: 4/20/2024 10:30:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GIOHANGCT](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[ID_HD] [int] NOT NULL,
	[IMEI] [varchar](15) NOT NULL,
	[Don_Gia] [int] NOT NULL,
	[Trang_Thai] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HOADON]    Script Date: 4/20/2024 10:30:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HOADON](
	[ID_HD] [int] IDENTITY(1,1) NOT NULL,
	[ID_NV] [int] NOT NULL,
	[ID_KH] [int] NULL,
	[Ngay_Tao] [date] NOT NULL,
	[Tong_Tien] [int] NULL,
	[Tong_Tien_khach_Dung] [int] NULL,
	[Ghi_Chu] [nvarchar](255) NULL,
	[Phuong_Thuc_TT] [int] NULL,
	[Trang_Thai] [int] NOT NULL,
	[ID_KMCT] [int] NULL,
	[Ngay_Sua] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_HD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[IMEI]    Script Date: 4/20/2024 10:30:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[IMEI](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[IMEI] [varchar](15) NOT NULL,
	[ID_SPCT] [int] NOT NULL,
	[Ngay_Tao] [date] NOT NULL,
	[ID_NV] [int] NOT NULL,
	[Trang_Thai] [int] NOT NULL,
	[Ngay_Sua] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KHACHHANG]    Script Date: 4/20/2024 10:30:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KHACHHANG](
	[ID_KH] [int] IDENTITY(1,1) NOT NULL,
	[ID_NV] [int] NOT NULL,
	[Ho_Ten] [nvarchar](50) NOT NULL,
	[SDT] [nvarchar](15) NOT NULL,
	[Ngay_sinh] [date] NULL,
	[Gioi_Tinh] [bit] NULL,
	[Trang_thai] [int] NOT NULL,
	[Ngay_Tao] [date] NOT NULL,
	[Ngay_Sua] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_KH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KHUYENMAI]    Script Date: 4/20/2024 10:30:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KHUYENMAI](
	[ID_KM] [int] IDENTITY(1,1) NOT NULL,
	[Ten_KM] [nvarchar](100) NOT NULL,
	[Ngay_Bat_Dau] [date] NOT NULL,
	[Ngay_Ket_Thuc] [date] NOT NULL,
	[Ngay_Tao] [date] NOT NULL,
	[ID_NV] [int] NOT NULL,
	[Ghi_Chu] [nvarchar](255) NULL,
	[Trang_Thai] [int] NOT NULL,
	[Ngay_Sua] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_KM] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KHUYENMAICT]    Script Date: 4/20/2024 10:30:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KHUYENMAICT](
	[ID_KMCT] [int] IDENTITY(1,1) NOT NULL,
	[ID_KM] [int] NOT NULL,
	[So_Luong] [int] NOT NULL,
	[Dieu_Kien_Gia] [int] NULL,
	[Gia_Khuyen_Mai] [int] NOT NULL,
	[Don_Vi_Khuyen_Mai] [int] NOT NULL,
	[Giam_Toi_Da] [int] NULL,
	[Dieu_Kien_So_Luong] [int] NULL,
	[Trang_Thai] [int] NOT NULL,
	[Ngay_Tao] [date] NOT NULL,
	[Ngay_Sua] [date] NULL,
	[Ma_Voucher] [varchar](50) NOT NULL,
	[Dieu_Kien_SP] [varchar](255) NULL,
	[Loai_Khuyen_Mai] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_KMCT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MANHINH]    Script Date: 4/20/2024 10:30:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MANHINH](
	[ID_Man_Hinh] [int] IDENTITY(1,1) NOT NULL,
	[Kich_Thuoc] [float] NOT NULL,
	[Trang_Thai] [int] NOT NULL,
	[Ngay_Sua] [date] NULL,
	[Ngay_Tao] [date] NOT NULL,
	[ID_Thong_So_Man_Hinh] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_Man_Hinh] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MAUSAC]    Script Date: 4/20/2024 10:30:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MAUSAC](
	[ID_Mau] [int] IDENTITY(1,1) NOT NULL,
	[Ten_Mau] [nvarchar](50) NOT NULL,
	[Trang_Thai] [int] NOT NULL,
	[Ngay_Tao] [date] NOT NULL,
	[Ngay_Sua] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_Mau] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MODELNUMBER]    Script Date: 4/20/2024 10:30:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MODELNUMBER](
	[ID_Model_Number] [int] IDENTITY(1,1) NOT NULL,
	[Ky_Hieu_Model_Number] [varchar](10) NOT NULL,
	[Dat_Nuoc] [nvarchar](50) NOT NULL,
	[Trang_Thai] [int] NOT NULL,
	[Ngay_Tao] [date] NOT NULL,
	[Ngay_Sua] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_Model_Number] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NHANVIEN]    Script Date: 4/20/2024 10:30:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NHANVIEN](
	[ID_NV] [int] IDENTITY(1,1) NOT NULL,
	[Ho_Ten] [nvarchar](50) NOT NULL,
	[SDT] [nvarchar](10) NOT NULL,
	[Ngay_Sinh] [date] NULL,
	[CCCD] [nvarchar](12) NOT NULL,
	[Dia_Chi] [nvarchar](255) NOT NULL,
	[Gioi_Tinh] [bit] NULL,
	[Trang_Thai] [int] NOT NULL,
	[Chuc_Vu] [int] NOT NULL,
	[Ngay_Tao] [date] NOT NULL,
	[Pass_word] [nvarchar](12) NOT NULL,
	[Ngay_Sua] [date] NULL,
	[anh] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_NV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PIN]    Script Date: 4/20/2024 10:30:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PIN](
	[ID_Pin] [int] IDENTITY(1,1) NOT NULL,
	[Loai_Pin] [int] NOT NULL,
	[Dung_Luong_Pin] [int] NOT NULL,
	[Trang_Thai] [int] NOT NULL,
	[Ngay_Sua] [date] NULL,
	[Ngay_Tao] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_Pin] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RAM]    Script Date: 4/20/2024 10:30:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RAM](
	[ID_Ram] [int] IDENTITY(1,1) NOT NULL,
	[Dung_Luong] [int] NOT NULL,
	[Trang_Thai] [int] NOT NULL,
	[Ngay_Tao] [date] NOT NULL,
	[Ngay_Sua] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_Ram] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ROM]    Script Date: 4/20/2024 10:30:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ROM](
	[ID_Rom] [int] IDENTITY(1,1) NOT NULL,
	[Dung_Luong] [int] NOT NULL,
	[Trang_Thai] [int] NOT NULL,
	[Ngay_Tao] [date] NOT NULL,
	[Ngay_Sua] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_Rom] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SANPHAM]    Script Date: 4/20/2024 10:30:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SANPHAM](
	[ID_San_Pham] [int] IDENTITY(1,1) NOT NULL,
	[ID_Chip] [int] NOT NULL,
	[ID_Pin] [int] NOT NULL,
	[ID_Man_Hinh] [int] NOT NULL,
	[ID_Cong_Sac] [int] NOT NULL,
	[Ho_Tro_Mang] [int] NOT NULL,
	[The_He] [int] NOT NULL,
	[So_Luong_Sim] [int] NOT NULL,
	[Trang_Thai] [int] NOT NULL,
	[Ngay_Tao] [date] NOT NULL,
	[Ngay_Sua] [date] NULL,
	[ID_Dong] [int] NOT NULL,
	[ID_NV] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_San_Pham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SANPHAMCHITIET]    Script Date: 4/20/2024 10:30:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SANPHAMCHITIET](
	[ID_SPCT] [int] IDENTITY(1,1) NOT NULL,
	[ID_San_Pham] [int] NOT NULL,
	[ID_Ram] [int] NOT NULL,
	[ID_Rom] [int] NOT NULL,
	[ID_Mau] [int] NOT NULL,
	[ID_Model_Number] [int] NOT NULL,
	[Version_HDH] [int] NULL,
	[Anh] [nvarchar](255) NULL,
	[Tinh_Trang] [int] NOT NULL,
	[Phien_Ban] [int] NOT NULL,
	[Gia_Nhap] [int] NOT NULL,
	[Don_Gia] [int] NOT NULL,
	[Trang_Thai] [int] NOT NULL,
	[Ngay_Tao] [date] NOT NULL,
	[Ngay_Sua] [date] NULL,
	[ID_NV] [int] NOT NULL,
	[mo_Ta] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_SPCT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[THONGSOMANHINH]    Script Date: 4/20/2024 10:30:10 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[THONGSOMANHINH](
	[ID_Thong_So_Man_Hinh] [int] IDENTITY(1,1) NOT NULL,
	[Loai_Man_Hinh] [int] NOT NULL,
	[Do_Phan_Giai] [int] NOT NULL,
	[Tan_So_Quet] [int] NOT NULL,
	[Trang_Thai] [int] NOT NULL,
	[Ngay_Sua] [date] NULL,
	[Ngay_Tao] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_Thong_So_Man_Hinh] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[CAMERA] ON 

INSERT [dbo].[CAMERA] ([ID], [ID_Camera], [ID_San_Pham]) VALUES (6, 1, 6)
INSERT [dbo].[CAMERA] ([ID], [ID_Camera], [ID_San_Pham]) VALUES (7, 2, 6)
INSERT [dbo].[CAMERA] ([ID], [ID_Camera], [ID_San_Pham]) VALUES (8, 1, 7)
INSERT [dbo].[CAMERA] ([ID], [ID_Camera], [ID_San_Pham]) VALUES (9, 2, 7)
INSERT [dbo].[CAMERA] ([ID], [ID_Camera], [ID_San_Pham]) VALUES (10, 1, 4)
INSERT [dbo].[CAMERA] ([ID], [ID_Camera], [ID_San_Pham]) VALUES (11, 2, 4)
INSERT [dbo].[CAMERA] ([ID], [ID_Camera], [ID_San_Pham]) VALUES (12, 3, 8)
INSERT [dbo].[CAMERA] ([ID], [ID_Camera], [ID_San_Pham]) VALUES (13, 4, 8)
INSERT [dbo].[CAMERA] ([ID], [ID_Camera], [ID_San_Pham]) VALUES (14, 1, 9)
INSERT [dbo].[CAMERA] ([ID], [ID_Camera], [ID_San_Pham]) VALUES (15, 2, 9)
INSERT [dbo].[CAMERA] ([ID], [ID_Camera], [ID_San_Pham]) VALUES (16, 3, 10)
INSERT [dbo].[CAMERA] ([ID], [ID_Camera], [ID_San_Pham]) VALUES (17, 4, 10)
INSERT [dbo].[CAMERA] ([ID], [ID_Camera], [ID_San_Pham]) VALUES (18, 1, 11)
INSERT [dbo].[CAMERA] ([ID], [ID_Camera], [ID_San_Pham]) VALUES (19, 2, 11)
INSERT [dbo].[CAMERA] ([ID], [ID_Camera], [ID_San_Pham]) VALUES (24, 1, 12)
INSERT [dbo].[CAMERA] ([ID], [ID_Camera], [ID_San_Pham]) VALUES (25, 4, 12)
INSERT [dbo].[CAMERA] ([ID], [ID_Camera], [ID_San_Pham]) VALUES (28, 3, 13)
INSERT [dbo].[CAMERA] ([ID], [ID_Camera], [ID_San_Pham]) VALUES (29, 2, 13)
SET IDENTITY_INSERT [dbo].[CAMERA] OFF
GO
SET IDENTITY_INSERT [dbo].[CAMERAALL] ON 

INSERT [dbo].[CAMERAALL] ([ID_Camera], [Loai_Camera], [So_Luong], [Thong_So_Ky_Thuat], [Trang_Thai], [Ngay_Sua], [Ngay_Tao]) VALUES (1, 0, 1, 12, 0, NULL, CAST(N'2024-03-20' AS Date))
INSERT [dbo].[CAMERAALL] ([ID_Camera], [Loai_Camera], [So_Luong], [Thong_So_Ky_Thuat], [Trang_Thai], [Ngay_Sua], [Ngay_Tao]) VALUES (2, 1, 3, 23, 0, CAST(N'2024-03-22' AS Date), CAST(N'2024-03-20' AS Date))
INSERT [dbo].[CAMERAALL] ([ID_Camera], [Loai_Camera], [So_Luong], [Thong_So_Ky_Thuat], [Trang_Thai], [Ngay_Sua], [Ngay_Tao]) VALUES (3, 0, 1, 16, 1, CAST(N'2024-04-13' AS Date), CAST(N'2024-03-22' AS Date))
INSERT [dbo].[CAMERAALL] ([ID_Camera], [Loai_Camera], [So_Luong], [Thong_So_Ky_Thuat], [Trang_Thai], [Ngay_Sua], [Ngay_Tao]) VALUES (4, 1, 2, 30, 0, CAST(N'2024-03-22' AS Date), CAST(N'2024-03-22' AS Date))
INSERT [dbo].[CAMERAALL] ([ID_Camera], [Loai_Camera], [So_Luong], [Thong_So_Ky_Thuat], [Trang_Thai], [Ngay_Sua], [Ngay_Tao]) VALUES (5, 0, 1, 5, 0, CAST(N'2024-04-13' AS Date), CAST(N'2024-04-13' AS Date))
INSERT [dbo].[CAMERAALL] ([ID_Camera], [Loai_Camera], [So_Luong], [Thong_So_Ky_Thuat], [Trang_Thai], [Ngay_Sua], [Ngay_Tao]) VALUES (6, 1, 1, 15, 0, CAST(N'2024-04-13' AS Date), CAST(N'2024-04-13' AS Date))
SET IDENTITY_INSERT [dbo].[CAMERAALL] OFF
GO
SET IDENTITY_INSERT [dbo].[CHIP] ON 

INSERT [dbo].[CHIP] ([ID_Chip], [Ten_Chip], [Trang_Thai], [Ngay_Sua], [Ngay_Tao]) VALUES (1, N'A14 Bionic', 0, CAST(N'2024-04-13' AS Date), CAST(N'2024-03-20' AS Date))
INSERT [dbo].[CHIP] ([ID_Chip], [Ten_Chip], [Trang_Thai], [Ngay_Sua], [Ngay_Tao]) VALUES (2, N'A16', 0, CAST(N'2024-04-13' AS Date), CAST(N'2024-04-13' AS Date))
SET IDENTITY_INSERT [dbo].[CHIP] OFF
GO
SET IDENTITY_INSERT [dbo].[CONGSAC] ON 

INSERT [dbo].[CONGSAC] ([ID_Cong_Sac], [Ten_Cong_Sac], [Cong_Nghe], [Trang_Thai], [Ngay_Sua], [Ngay_Tao]) VALUES (1, N'Lightning', 20, 0, NULL, CAST(N'2024-04-09' AS Date))
SET IDENTITY_INSERT [dbo].[CONGSAC] OFF
GO
SET IDENTITY_INSERT [dbo].[DONG] ON 

INSERT [dbo].[DONG] ([ID_Dong], [Trang_Thai], [Ten_Dong], [Ngay_Sua], [Ngay_Tao]) VALUES (1, 0, N'Pro Max', NULL, CAST(N'2024-03-20' AS Date))
INSERT [dbo].[DONG] ([ID_Dong], [Trang_Thai], [Ten_Dong], [Ngay_Sua], [Ngay_Tao]) VALUES (2, 0, N'Pro', NULL, CAST(N'2024-03-20' AS Date))
INSERT [dbo].[DONG] ([ID_Dong], [Trang_Thai], [Ten_Dong], [Ngay_Sua], [Ngay_Tao]) VALUES (3, 0, N'Thuong', NULL, CAST(N'2024-03-22' AS Date))
INSERT [dbo].[DONG] ([ID_Dong], [Trang_Thai], [Ten_Dong], [Ngay_Sua], [Ngay_Tao]) VALUES (4, 0, N'Plus', NULL, CAST(N'2024-03-22' AS Date))
SET IDENTITY_INSERT [dbo].[DONG] OFF
GO
SET IDENTITY_INSERT [dbo].[GIOHANGCT] ON 

INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (29, 9, N'123456789999991', 10000000, 0)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (30, 9, N'123456789009718', 1000000, 0)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (31, 10, N'123456789009711', 1000000, 0)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (32, 10, N'123456789009715', 1000000, 0)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (33, 12, N'012345678912345', 1000000, 0)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (34, 14, N'012345678912000', 1000000, 1)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (35, 14, N'123456789012346', 1000000, 1)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (36, 14, N'123456789012346', 1000000, 1)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (37, 14, N'123456789012346', 1000000, 0)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (38, 14, N'123456789012348', 1000000, 0)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (39, 15, N'123456789009767', 1000000, 0)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (40, 16, N'012345678911111', 100, 0)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (41, 16, N'123456789054327', 1000000, 0)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (42, 16, N'123456789009712', 1000000, 0)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (43, 17, N'123456789012678', 10000000, 0)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (44, 17, N'123456789009713', 1000000, 0)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (45, 19, N'222222222222222', 1000000, 1)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (46, 19, N'222222222222222', 1000000, 1)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (47, 19, N'123456789012345', 1000000, 1)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (48, 19, N'123456789012345', 1000000, 1)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (49, 19, N'222222222222222', 1000000, 1)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (50, 19, N'222222222222222', 1000000, 1)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (51, 22, N'123456789012345', 1000000, 0)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (52, 22, N'012345678912000', 1000000, 1)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (53, 22, N'012345678912000', 1000000, 0)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (54, 23, N'123456789009766', 1000000, 0)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (55, 23, N'123456789009769', 1000000, 0)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (56, 24, N'123456789009768', 1000000, 0)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (57, 24, N'123456789009717', 1000000, 0)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (58, 25, N'123456789009714', 1000000, 0)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (59, 27, N'123458789009773', 25000000, 0)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (60, 27, N'123456789009773', 20000000, 0)
INSERT [dbo].[GIOHANGCT] ([ID], [ID_HD], [IMEI], [Don_Gia], [Trang_Thai]) VALUES (61, 28, N'123456789016786', 22000000, 0)
SET IDENTITY_INSERT [dbo].[GIOHANGCT] OFF
GO
SET IDENTITY_INSERT [dbo].[HOADON] ON 

INSERT [dbo].[HOADON] ([ID_HD], [ID_NV], [ID_KH], [Ngay_Tao], [Tong_Tien], [Tong_Tien_khach_Dung], [Ghi_Chu], [Phuong_Thuc_TT], [Trang_Thai], [ID_KMCT], [Ngay_Sua]) VALUES (9, 4, 4, CAST(N'2024-04-06' AS Date), 10900000, 12000000, N'khachs coit', 0, 1, 4, CAST(N'2024-04-13' AS Date))
INSERT [dbo].[HOADON] ([ID_HD], [ID_NV], [ID_KH], [Ngay_Tao], [Tong_Tien], [Tong_Tien_khach_Dung], [Ghi_Chu], [Phuong_Thuc_TT], [Trang_Thai], [ID_KMCT], [Ngay_Sua]) VALUES (10, 4, 4, CAST(N'2024-04-06' AS Date), 2000000, 2000000, N'', 0, 1, NULL, CAST(N'2024-04-08' AS Date))
INSERT [dbo].[HOADON] ([ID_HD], [ID_NV], [ID_KH], [Ngay_Tao], [Tong_Tien], [Tong_Tien_khach_Dung], [Ghi_Chu], [Phuong_Thuc_TT], [Trang_Thai], [ID_KMCT], [Ngay_Sua]) VALUES (12, 2, 6, CAST(N'2024-04-08' AS Date), 1000000, 1000000, N'', 0, 1, NULL, CAST(N'2024-04-08' AS Date))
INSERT [dbo].[HOADON] ([ID_HD], [ID_NV], [ID_KH], [Ngay_Tao], [Tong_Tien], [Tong_Tien_khach_Dung], [Ghi_Chu], [Phuong_Thuc_TT], [Trang_Thai], [ID_KMCT], [Ngay_Sua]) VALUES (14, 2, 6, CAST(N'2024-04-09' AS Date), 2000000, 2000000, N'', 0, 1, NULL, CAST(N'2024-04-09' AS Date))
INSERT [dbo].[HOADON] ([ID_HD], [ID_NV], [ID_KH], [Ngay_Tao], [Tong_Tien], [Tong_Tien_khach_Dung], [Ghi_Chu], [Phuong_Thuc_TT], [Trang_Thai], [ID_KMCT], [Ngay_Sua]) VALUES (15, 2, 6, CAST(N'2024-04-09' AS Date), 1000000, NULL, N'', 1, 1, NULL, CAST(N'2024-04-09' AS Date))
INSERT [dbo].[HOADON] ([ID_HD], [ID_NV], [ID_KH], [Ngay_Tao], [Tong_Tien], [Tong_Tien_khach_Dung], [Ghi_Chu], [Phuong_Thuc_TT], [Trang_Thai], [ID_KMCT], [Ngay_Sua]) VALUES (16, 2, 6, CAST(N'2024-04-09' AS Date), 2000100, 2000100, N'', 0, 1, NULL, CAST(N'2024-04-09' AS Date))
INSERT [dbo].[HOADON] ([ID_HD], [ID_NV], [ID_KH], [Ngay_Tao], [Tong_Tien], [Tong_Tien_khach_Dung], [Ghi_Chu], [Phuong_Thuc_TT], [Trang_Thai], [ID_KMCT], [Ngay_Sua]) VALUES (17, 4, 6, CAST(N'2024-04-09' AS Date), 11000000, 11100000, N'abc', 0, 1, NULL, CAST(N'2024-04-13' AS Date))
INSERT [dbo].[HOADON] ([ID_HD], [ID_NV], [ID_KH], [Ngay_Tao], [Tong_Tien], [Tong_Tien_khach_Dung], [Ghi_Chu], [Phuong_Thuc_TT], [Trang_Thai], [ID_KMCT], [Ngay_Sua]) VALUES (18, 4, NULL, CAST(N'2024-04-09' AS Date), NULL, NULL, N'vzxk.jcvj.c', NULL, 2, NULL, CAST(N'2024-04-13' AS Date))
INSERT [dbo].[HOADON] ([ID_HD], [ID_NV], [ID_KH], [Ngay_Tao], [Tong_Tien], [Tong_Tien_khach_Dung], [Ghi_Chu], [Phuong_Thuc_TT], [Trang_Thai], [ID_KMCT], [Ngay_Sua]) VALUES (19, 4, NULL, CAST(N'2024-04-09' AS Date), NULL, NULL, N'aaaa', NULL, 2, NULL, CAST(N'2024-04-13' AS Date))
INSERT [dbo].[HOADON] ([ID_HD], [ID_NV], [ID_KH], [Ngay_Tao], [Tong_Tien], [Tong_Tien_khach_Dung], [Ghi_Chu], [Phuong_Thuc_TT], [Trang_Thai], [ID_KMCT], [Ngay_Sua]) VALUES (21, 2, NULL, CAST(N'2024-04-13' AS Date), NULL, NULL, N'aaa', NULL, 2, NULL, CAST(N'2024-04-13' AS Date))
INSERT [dbo].[HOADON] ([ID_HD], [ID_NV], [ID_KH], [Ngay_Tao], [Tong_Tien], [Tong_Tien_khach_Dung], [Ghi_Chu], [Phuong_Thuc_TT], [Trang_Thai], [ID_KMCT], [Ngay_Sua]) VALUES (22, 2, 4, CAST(N'2024-04-13' AS Date), 1000000, NULL, N'', 1, 1, 5, CAST(N'2024-04-13' AS Date))
INSERT [dbo].[HOADON] ([ID_HD], [ID_NV], [ID_KH], [Ngay_Tao], [Tong_Tien], [Tong_Tien_khach_Dung], [Ghi_Chu], [Phuong_Thuc_TT], [Trang_Thai], [ID_KMCT], [Ngay_Sua]) VALUES (23, 2, 6, CAST(N'2024-04-13' AS Date), 1000000, NULL, N'', 1, 1, 5, CAST(N'2024-04-13' AS Date))
INSERT [dbo].[HOADON] ([ID_HD], [ID_NV], [ID_KH], [Ngay_Tao], [Tong_Tien], [Tong_Tien_khach_Dung], [Ghi_Chu], [Phuong_Thuc_TT], [Trang_Thai], [ID_KMCT], [Ngay_Sua]) VALUES (24, 2, 6, CAST(N'2024-04-13' AS Date), 1000000, NULL, N'khach doi y', 1, 1, 5, CAST(N'2024-04-13' AS Date))
INSERT [dbo].[HOADON] ([ID_HD], [ID_NV], [ID_KH], [Ngay_Tao], [Tong_Tien], [Tong_Tien_khach_Dung], [Ghi_Chu], [Phuong_Thuc_TT], [Trang_Thai], [ID_KMCT], [Ngay_Sua]) VALUES (25, 2, 6, CAST(N'2024-04-13' AS Date), 0, 0, N'', 0, 1, 5, CAST(N'2024-04-13' AS Date))
INSERT [dbo].[HOADON] ([ID_HD], [ID_NV], [ID_KH], [Ngay_Tao], [Tong_Tien], [Tong_Tien_khach_Dung], [Ghi_Chu], [Phuong_Thuc_TT], [Trang_Thai], [ID_KMCT], [Ngay_Sua]) VALUES (26, 2, NULL, CAST(N'2024-04-13' AS Date), NULL, NULL, N'khach doi y', NULL, 2, NULL, CAST(N'2024-04-13' AS Date))
INSERT [dbo].[HOADON] ([ID_HD], [ID_NV], [ID_KH], [Ngay_Tao], [Tong_Tien], [Tong_Tien_khach_Dung], [Ghi_Chu], [Phuong_Thuc_TT], [Trang_Thai], [ID_KMCT], [Ngay_Sua]) VALUES (27, 2, 4, CAST(N'2024-04-13' AS Date), 44000000, 50000000, N'', 0, 1, 5, CAST(N'2024-04-13' AS Date))
INSERT [dbo].[HOADON] ([ID_HD], [ID_NV], [ID_KH], [Ngay_Tao], [Tong_Tien], [Tong_Tien_khach_Dung], [Ghi_Chu], [Phuong_Thuc_TT], [Trang_Thai], [ID_KMCT], [Ngay_Sua]) VALUES (28, 2, 4, CAST(N'2024-04-13' AS Date), 21000000, NULL, N'', 1, 1, 5, CAST(N'2024-04-13' AS Date))
SET IDENTITY_INSERT [dbo].[HOADON] OFF
GO
SET IDENTITY_INSERT [dbo].[IMEI] ON 

INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (49, N'111111111111111', 2, CAST(N'2024-03-20' AS Date), 2, 0, NULL)
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (50, N'222222222222222', 2, CAST(N'2024-03-21' AS Date), 2, 0, CAST(N'2024-04-13' AS Date))
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (51, N'123456789012345', 2, CAST(N'2024-03-21' AS Date), 2, 2, CAST(N'2024-04-13' AS Date))
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (52, N'123456789012346', 2, CAST(N'2024-03-23' AS Date), 2, 2, NULL)
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (53, N'123456789012348', 2, CAST(N'2024-03-23' AS Date), 2, 2, NULL)
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (54, N'012345678912000', 2, CAST(N'2024-03-23' AS Date), 2, 2, CAST(N'2024-04-13' AS Date))
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (55, N'012345678912345', 2, CAST(N'2024-03-23' AS Date), 2, 2, NULL)
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (56, N'123456789009766', 2, CAST(N'2024-03-25' AS Date), 2, 2, CAST(N'2024-04-13' AS Date))
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (57, N'123456789009767', 2, CAST(N'2024-03-25' AS Date), 2, 2, NULL)
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (58, N'123456789009769', 2, CAST(N'2024-03-25' AS Date), 2, 2, CAST(N'2024-04-13' AS Date))
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (59, N'123456789009768', 2, CAST(N'2024-03-25' AS Date), 2, 2, CAST(N'2024-04-13' AS Date))
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (60, N'123456789009717', 2, CAST(N'2024-03-25' AS Date), 2, 2, CAST(N'2024-04-13' AS Date))
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (61, N'123456789009718', 2, CAST(N'2024-03-25' AS Date), 2, 2, NULL)
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (62, N'123456789009711', 2, CAST(N'2024-03-25' AS Date), 2, 2, NULL)
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (63, N'123456789009712', 2, CAST(N'2024-03-25' AS Date), 2, 2, NULL)
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (64, N'123456789009713', 2, CAST(N'2024-03-25' AS Date), 2, 2, NULL)
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (65, N'123456789009714', 2, CAST(N'2024-03-25' AS Date), 2, 2, CAST(N'2024-04-13' AS Date))
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (66, N'123456789009715', 2, CAST(N'2024-03-25' AS Date), 2, 2, NULL)
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (67, N'123456789016786', 2, CAST(N'2024-03-26' AS Date), 2, 2, CAST(N'2024-04-13' AS Date))
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (72, N'123456789999991', 7, CAST(N'2024-03-26' AS Date), 2, 2, NULL)
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (76, N'123456789012678', 7, CAST(N'2024-04-09' AS Date), 4, 2, NULL)
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (77, N'123456789054327', 6, CAST(N'2024-04-09' AS Date), 4, 2, NULL)
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (78, N'012345678911111', 8, CAST(N'2024-04-09' AS Date), 4, 2, NULL)
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (91, N'123456789009773', 9, CAST(N'2024-04-13' AS Date), 2, 2, CAST(N'2024-04-13' AS Date))
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (92, N'123456789009764', 9, CAST(N'2024-04-13' AS Date), 2, 0, NULL)
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (93, N'123456789003715', 9, CAST(N'2024-04-13' AS Date), 2, 0, NULL)
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (94, N'123456789002715', 9, CAST(N'2024-04-13' AS Date), 2, 0, NULL)
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (95, N'123446789009715', 10, CAST(N'2024-04-13' AS Date), 2, 0, NULL)
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (96, N'123458789009773', 10, CAST(N'2024-04-13' AS Date), 2, 2, CAST(N'2024-04-13' AS Date))
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (97, N'123456789109764', 10, CAST(N'2024-04-13' AS Date), 2, 0, NULL)
INSERT [dbo].[IMEI] ([id], [IMEI], [ID_SPCT], [Ngay_Tao], [ID_NV], [Trang_Thai], [Ngay_Sua]) VALUES (98, N'123456789003725', 10, CAST(N'2024-04-13' AS Date), 2, 0, NULL)
SET IDENTITY_INSERT [dbo].[IMEI] OFF
GO
SET IDENTITY_INSERT [dbo].[KHACHHANG] ON 

INSERT [dbo].[KHACHHANG] ([ID_KH], [ID_NV], [Ho_Ten], [SDT], [Ngay_sinh], [Gioi_Tinh], [Trang_thai], [Ngay_Tao], [Ngay_Sua]) VALUES (4, 4, N'abc', N'0393531151', CAST(N'2004-09-22' AS Date), 0, 0, CAST(N'2024-03-31' AS Date), NULL)
INSERT [dbo].[KHACHHANG] ([ID_KH], [ID_NV], [Ho_Ten], [SDT], [Ngay_sinh], [Gioi_Tinh], [Trang_thai], [Ngay_Tao], [Ngay_Sua]) VALUES (6, 2, N'Khách vãng lai', N'0000000000', CAST(N'2004-09-22' AS Date), 0, 0, CAST(N'2024-03-31' AS Date), NULL)
INSERT [dbo].[KHACHHANG] ([ID_KH], [ID_NV], [Ho_Ten], [SDT], [Ngay_sinh], [Gioi_Tinh], [Trang_thai], [Ngay_Tao], [Ngay_Sua]) VALUES (7, 2, N'khg', N'0393531157', CAST(N'2024-04-07' AS Date), 1, 0, CAST(N'2024-04-07' AS Date), NULL)
INSERT [dbo].[KHACHHANG] ([ID_KH], [ID_NV], [Ho_Ten], [SDT], [Ngay_sinh], [Gioi_Tinh], [Trang_thai], [Ngay_Tao], [Ngay_Sua]) VALUES (8, 2, N'Phong khach sop', N'0393531153', CAST(N'2000-04-07' AS Date), 0, 0, CAST(N'2024-04-13' AS Date), NULL)
INSERT [dbo].[KHACHHANG] ([ID_KH], [ID_NV], [Ho_Ten], [SDT], [Ngay_sinh], [Gioi_Tinh], [Trang_thai], [Ngay_Tao], [Ngay_Sua]) VALUES (9, 2, N'Lam', N'0343457888', CAST(N'2000-09-22' AS Date), 0, 0, CAST(N'2024-04-13' AS Date), CAST(N'2024-04-13' AS Date))
SET IDENTITY_INSERT [dbo].[KHACHHANG] OFF
GO
SET IDENTITY_INSERT [dbo].[KHUYENMAI] ON 

INSERT [dbo].[KHUYENMAI] ([ID_KM], [Ten_KM], [Ngay_Bat_Dau], [Ngay_Ket_Thuc], [Ngay_Tao], [ID_NV], [Ghi_Chu], [Trang_Thai], [Ngay_Sua]) VALUES (10, N'a', CAST(N'2024-03-27' AS Date), CAST(N'2024-03-27' AS Date), CAST(N'2024-03-27' AS Date), 2, NULL, 0, NULL)
INSERT [dbo].[KHUYENMAI] ([ID_KM], [Ten_KM], [Ngay_Bat_Dau], [Ngay_Ket_Thuc], [Ngay_Tao], [ID_NV], [Ghi_Chu], [Trang_Thai], [Ngay_Sua]) VALUES (11, N'Summer2024', CAST(N'2024-03-27' AS Date), CAST(N'2024-03-30' AS Date), CAST(N'2024-03-27' AS Date), 2, NULL, 0, CAST(N'2024-03-27' AS Date))
INSERT [dbo].[KHUYENMAI] ([ID_KM], [Ten_KM], [Ngay_Bat_Dau], [Ngay_Ket_Thuc], [Ngay_Tao], [ID_NV], [Ghi_Chu], [Trang_Thai], [Ngay_Sua]) VALUES (12, N'a', CAST(N'2024-03-12' AS Date), CAST(N'2024-03-13' AS Date), CAST(N'2024-03-27' AS Date), 2, NULL, 0, NULL)
INSERT [dbo].[KHUYENMAI] ([ID_KM], [Ten_KM], [Ngay_Bat_Dau], [Ngay_Ket_Thuc], [Ngay_Tao], [ID_NV], [Ghi_Chu], [Trang_Thai], [Ngay_Sua]) VALUES (13, N'Summer2024', CAST(N'2024-03-27' AS Date), CAST(N'2024-03-30' AS Date), CAST(N'2024-03-27' AS Date), 2, N'Nhằm thúc đẩy mua bán hàng hóa nhanh gọn lẹ
OK con dê', 0, CAST(N'2024-03-28' AS Date))
INSERT [dbo].[KHUYENMAI] ([ID_KM], [Ten_KM], [Ngay_Bat_Dau], [Ngay_Ket_Thuc], [Ngay_Tao], [ID_NV], [Ghi_Chu], [Trang_Thai], [Ngay_Sua]) VALUES (14, N'b', CAST(N'2024-03-27' AS Date), CAST(N'2024-03-27' AS Date), CAST(N'2024-03-29' AS Date), 2, NULL, 0, NULL)
INSERT [dbo].[KHUYENMAI] ([ID_KM], [Ten_KM], [Ngay_Bat_Dau], [Ngay_Ket_Thuc], [Ngay_Tao], [ID_NV], [Ghi_Chu], [Trang_Thai], [Ngay_Sua]) VALUES (15, N'DangLamThuBanHang', CAST(N'2024-04-01' AS Date), CAST(N'2024-04-08' AS Date), CAST(N'2024-04-01' AS Date), 2, NULL, 0, NULL)
INSERT [dbo].[KHUYENMAI] ([ID_KM], [Ten_KM], [Ngay_Bat_Dau], [Ngay_Ket_Thuc], [Ngay_Tao], [ID_NV], [Ghi_Chu], [Trang_Thai], [Ngay_Sua]) VALUES (16, N'ABC', CAST(N'2024-04-09' AS Date), CAST(N'2024-04-23' AS Date), CAST(N'2024-04-09' AS Date), 2, N'hello', 0, NULL)
INSERT [dbo].[KHUYENMAI] ([ID_KM], [Ten_KM], [Ngay_Bat_Dau], [Ngay_Ket_Thuc], [Ngay_Tao], [ID_NV], [Ghi_Chu], [Trang_Thai], [Ngay_Sua]) VALUES (17, N'PhongDay', CAST(N'2024-04-13' AS Date), CAST(N'2024-04-13' AS Date), CAST(N'2024-04-13' AS Date), 2, NULL, 0, CAST(N'2024-04-13' AS Date))
INSERT [dbo].[KHUYENMAI] ([ID_KM], [Ten_KM], [Ngay_Bat_Dau], [Ngay_Ket_Thuc], [Ngay_Tao], [ID_NV], [Ghi_Chu], [Trang_Thai], [Ngay_Sua]) VALUES (18, N'PhongDay', CAST(N'2024-04-13' AS Date), CAST(N'2024-04-13' AS Date), CAST(N'2024-04-13' AS Date), 2, NULL, 0, NULL)
INSERT [dbo].[KHUYENMAI] ([ID_KM], [Ten_KM], [Ngay_Bat_Dau], [Ngay_Ket_Thuc], [Ngay_Tao], [ID_NV], [Ghi_Chu], [Trang_Thai], [Ngay_Sua]) VALUES (19, N'summer2024', CAST(N'2024-04-13' AS Date), CAST(N'2024-04-13' AS Date), CAST(N'2024-04-13' AS Date), 2, NULL, 0, NULL)
SET IDENTITY_INSERT [dbo].[KHUYENMAI] OFF
GO
SET IDENTITY_INSERT [dbo].[KHUYENMAICT] ON 

INSERT [dbo].[KHUYENMAICT] ([ID_KMCT], [ID_KM], [So_Luong], [Dieu_Kien_Gia], [Gia_Khuyen_Mai], [Don_Vi_Khuyen_Mai], [Giam_Toi_Da], [Dieu_Kien_So_Luong], [Trang_Thai], [Ngay_Tao], [Ngay_Sua], [Ma_Voucher], [Dieu_Kien_SP], [Loai_Khuyen_Mai]) VALUES (1, 13, 100, 1, 5000000, 0, NULL, 1, 0, CAST(N'2024-03-28' AS Date), CAST(N'2024-03-29' AS Date), N'a', N'9,7,6,4,2', 1)
INSERT [dbo].[KHUYENMAICT] ([ID_KMCT], [ID_KM], [So_Luong], [Dieu_Kien_Gia], [Gia_Khuyen_Mai], [Don_Vi_Khuyen_Mai], [Giam_Toi_Da], [Dieu_Kien_So_Luong], [Trang_Thai], [Ngay_Tao], [Ngay_Sua], [Ma_Voucher], [Dieu_Kien_SP], [Loai_Khuyen_Mai]) VALUES (3, 13, 100, 0, 100000, 0, NULL, 1, 0, CAST(N'2024-03-28' AS Date), CAST(N'2024-03-29' AS Date), N'testThu', NULL, 0)
INSERT [dbo].[KHUYENMAICT] ([ID_KMCT], [ID_KM], [So_Luong], [Dieu_Kien_Gia], [Gia_Khuyen_Mai], [Don_Vi_Khuyen_Mai], [Giam_Toi_Da], [Dieu_Kien_So_Luong], [Trang_Thai], [Ngay_Tao], [Ngay_Sua], [Ma_Voucher], [Dieu_Kien_SP], [Loai_Khuyen_Mai]) VALUES (4, 15, 99, 10000, 100000, 0, NULL, 2, 0, CAST(N'2024-04-01' AS Date), NULL, N'testThoi', NULL, 0)
INSERT [dbo].[KHUYENMAICT] ([ID_KMCT], [ID_KM], [So_Luong], [Dieu_Kien_Gia], [Gia_Khuyen_Mai], [Don_Vi_Khuyen_Mai], [Giam_Toi_Da], [Dieu_Kien_So_Luong], [Trang_Thai], [Ngay_Tao], [Ngay_Sua], [Ma_Voucher], [Dieu_Kien_SP], [Loai_Khuyen_Mai]) VALUES (5, 16, 96, 0, 1000000, 0, NULL, 1, 0, CAST(N'2024-04-09' AS Date), CAST(N'2024-04-13' AS Date), N'testVoucher', NULL, 0)
INSERT [dbo].[KHUYENMAICT] ([ID_KMCT], [ID_KM], [So_Luong], [Dieu_Kien_Gia], [Gia_Khuyen_Mai], [Don_Vi_Khuyen_Mai], [Giam_Toi_Da], [Dieu_Kien_So_Luong], [Trang_Thai], [Ngay_Tao], [Ngay_Sua], [Ma_Voucher], [Dieu_Kien_SP], [Loai_Khuyen_Mai]) VALUES (6, 19, 100, 0, 1500000, 0, NULL, 2, 0, CAST(N'2024-04-13' AS Date), CAST(N'2024-04-13' AS Date), N'ph123', NULL, 0)
INSERT [dbo].[KHUYENMAICT] ([ID_KMCT], [ID_KM], [So_Luong], [Dieu_Kien_Gia], [Gia_Khuyen_Mai], [Don_Vi_Khuyen_Mai], [Giam_Toi_Da], [Dieu_Kien_So_Luong], [Trang_Thai], [Ngay_Tao], [Ngay_Sua], [Ma_Voucher], [Dieu_Kien_SP], [Loai_Khuyen_Mai]) VALUES (7, 19, 100, NULL, 1500000, 0, NULL, 2, 0, CAST(N'2024-04-13' AS Date), NULL, N'ph453', N'10,9', 1)
SET IDENTITY_INSERT [dbo].[KHUYENMAICT] OFF
GO
SET IDENTITY_INSERT [dbo].[MANHINH] ON 

INSERT [dbo].[MANHINH] ([ID_Man_Hinh], [Kich_Thuoc], [Trang_Thai], [Ngay_Sua], [Ngay_Tao], [ID_Thong_So_Man_Hinh]) VALUES (1, 6.3, 0, NULL, CAST(N'2024-03-20' AS Date), 1)
INSERT [dbo].[MANHINH] ([ID_Man_Hinh], [Kich_Thuoc], [Trang_Thai], [Ngay_Sua], [Ngay_Tao], [ID_Thong_So_Man_Hinh]) VALUES (2, 2, 0, CAST(N'2024-03-22' AS Date), CAST(N'2024-03-22' AS Date), 3)
INSERT [dbo].[MANHINH] ([ID_Man_Hinh], [Kich_Thuoc], [Trang_Thai], [Ngay_Sua], [Ngay_Tao], [ID_Thong_So_Man_Hinh]) VALUES (3, 8, 0, NULL, CAST(N'2024-03-22' AS Date), 1)
INSERT [dbo].[MANHINH] ([ID_Man_Hinh], [Kich_Thuoc], [Trang_Thai], [Ngay_Sua], [Ngay_Tao], [ID_Thong_So_Man_Hinh]) VALUES (4, 9, 0, NULL, CAST(N'2024-03-22' AS Date), 4)
INSERT [dbo].[MANHINH] ([ID_Man_Hinh], [Kich_Thuoc], [Trang_Thai], [Ngay_Sua], [Ngay_Tao], [ID_Thong_So_Man_Hinh]) VALUES (5, 1, 0, NULL, CAST(N'2024-03-22' AS Date), 1)
SET IDENTITY_INSERT [dbo].[MANHINH] OFF
GO
SET IDENTITY_INSERT [dbo].[MAUSAC] ON 

INSERT [dbo].[MAUSAC] ([ID_Mau], [Ten_Mau], [Trang_Thai], [Ngay_Tao], [Ngay_Sua]) VALUES (1, N'Xanh', 0, CAST(N'2024-03-20' AS Date), NULL)
INSERT [dbo].[MAUSAC] ([ID_Mau], [Ten_Mau], [Trang_Thai], [Ngay_Tao], [Ngay_Sua]) VALUES (2, N'Đỏ', 0, CAST(N'2024-03-20' AS Date), NULL)
SET IDENTITY_INSERT [dbo].[MAUSAC] OFF
GO
SET IDENTITY_INSERT [dbo].[MODELNUMBER] ON 

INSERT [dbo].[MODELNUMBER] ([ID_Model_Number], [Ky_Hieu_Model_Number], [Dat_Nuoc], [Trang_Thai], [Ngay_Tao], [Ngay_Sua]) VALUES (1, N'LL/A', N'America', 0, CAST(N'2024-03-20' AS Date), NULL)
INSERT [dbo].[MODELNUMBER] ([ID_Model_Number], [Ky_Hieu_Model_Number], [Dat_Nuoc], [Trang_Thai], [Ngay_Tao], [Ngay_Sua]) VALUES (2, N'JP/N', N'Japan', 0, CAST(N'2024-03-20' AS Date), NULL)
SET IDENTITY_INSERT [dbo].[MODELNUMBER] OFF
GO
SET IDENTITY_INSERT [dbo].[NHANVIEN] ON 

INSERT [dbo].[NHANVIEN] ([ID_NV], [Ho_Ten], [SDT], [Ngay_Sinh], [CCCD], [Dia_Chi], [Gioi_Tinh], [Trang_Thai], [Chuc_Vu], [Ngay_Tao], [Pass_word], [Ngay_Sua], [anh]) VALUES (2, N'Mai Thế Phong', N'0393531151', CAST(N'2004-09-22' AS Date), N'036204009876', N'Hà Nội', 0, 0, 0, CAST(N'2024-03-20' AS Date), N'Phong123', CAST(N'2024-04-11' AS Date), N'anh2.png')
INSERT [dbo].[NHANVIEN] ([ID_NV], [Ho_Ten], [SDT], [Ngay_Sinh], [CCCD], [Dia_Chi], [Gioi_Tinh], [Trang_Thai], [Chuc_Vu], [Ngay_Tao], [Pass_word], [Ngay_Sua], [anh]) VALUES (3, N'Mai Thế Phong    ', N'1234567890', CAST(N'2004-09-22' AS Date), N'012345678901', N'Hà Nội', 1, 0, 1, CAST(N'2024-03-22' AS Date), N'12345678', NULL, NULL)
INSERT [dbo].[NHANVIEN] ([ID_NV], [Ho_Ten], [SDT], [Ngay_Sinh], [CCCD], [Dia_Chi], [Gioi_Tinh], [Trang_Thai], [Chuc_Vu], [Ngay_Tao], [Pass_word], [Ngay_Sua], [anh]) VALUES (4, N'Mih', N'0234567899', CAST(N'2004-09-22' AS Date), N'012345678907', N'Hà Nội', 1, 0, 1, CAST(N'2024-03-22' AS Date), N'jh12345678', CAST(N'2024-04-05' AS Date), N'anh1.png')
INSERT [dbo].[NHANVIEN] ([ID_NV], [Ho_Ten], [SDT], [Ngay_Sinh], [CCCD], [Dia_Chi], [Gioi_Tinh], [Trang_Thai], [Chuc_Vu], [Ngay_Tao], [Pass_word], [Ngay_Sua], [anh]) VALUES (5, N'Mai Thế Phong', N'0393531157', CAST(N'2004-09-22' AS Date), N'036204009890', N'Hà Nội', 0, 0, 0, CAST(N'2024-03-22' AS Date), N'12377887', CAST(N'2024-03-22' AS Date), NULL)
SET IDENTITY_INSERT [dbo].[NHANVIEN] OFF
GO
SET IDENTITY_INSERT [dbo].[PIN] ON 

INSERT [dbo].[PIN] ([ID_Pin], [Loai_Pin], [Dung_Luong_Pin], [Trang_Thai], [Ngay_Sua], [Ngay_Tao]) VALUES (1, 0, 3000, 0, NULL, CAST(N'2024-03-20' AS Date))
INSERT [dbo].[PIN] ([ID_Pin], [Loai_Pin], [Dung_Luong_Pin], [Trang_Thai], [Ngay_Sua], [Ngay_Tao]) VALUES (2, 1, 3000, 0, NULL, CAST(N'2024-03-21' AS Date))
SET IDENTITY_INSERT [dbo].[PIN] OFF
GO
SET IDENTITY_INSERT [dbo].[RAM] ON 

INSERT [dbo].[RAM] ([ID_Ram], [Dung_Luong], [Trang_Thai], [Ngay_Tao], [Ngay_Sua]) VALUES (1, 64, 0, CAST(N'2024-03-20' AS Date), NULL)
INSERT [dbo].[RAM] ([ID_Ram], [Dung_Luong], [Trang_Thai], [Ngay_Tao], [Ngay_Sua]) VALUES (2, 128, 0, CAST(N'2024-03-20' AS Date), NULL)
INSERT [dbo].[RAM] ([ID_Ram], [Dung_Luong], [Trang_Thai], [Ngay_Tao], [Ngay_Sua]) VALUES (3, 516, 0, CAST(N'2024-03-22' AS Date), NULL)
INSERT [dbo].[RAM] ([ID_Ram], [Dung_Luong], [Trang_Thai], [Ngay_Tao], [Ngay_Sua]) VALUES (4, 6, 0, CAST(N'2024-04-13' AS Date), NULL)
SET IDENTITY_INSERT [dbo].[RAM] OFF
GO
SET IDENTITY_INSERT [dbo].[ROM] ON 

INSERT [dbo].[ROM] ([ID_Rom], [Dung_Luong], [Trang_Thai], [Ngay_Tao], [Ngay_Sua]) VALUES (1, 6, 0, CAST(N'2024-03-20' AS Date), NULL)
INSERT [dbo].[ROM] ([ID_Rom], [Dung_Luong], [Trang_Thai], [Ngay_Tao], [Ngay_Sua]) VALUES (2, 4, 0, CAST(N'2024-03-20' AS Date), NULL)
INSERT [dbo].[ROM] ([ID_Rom], [Dung_Luong], [Trang_Thai], [Ngay_Tao], [Ngay_Sua]) VALUES (3, 8, 0, CAST(N'2024-03-30' AS Date), NULL)
INSERT [dbo].[ROM] ([ID_Rom], [Dung_Luong], [Trang_Thai], [Ngay_Tao], [Ngay_Sua]) VALUES (4, 512, 0, CAST(N'2024-04-13' AS Date), NULL)
SET IDENTITY_INSERT [dbo].[ROM] OFF
GO
SET IDENTITY_INSERT [dbo].[SANPHAM] ON 

INSERT [dbo].[SANPHAM] ([ID_San_Pham], [ID_Chip], [ID_Pin], [ID_Man_Hinh], [ID_Cong_Sac], [Ho_Tro_Mang], [The_He], [So_Luong_Sim], [Trang_Thai], [Ngay_Tao], [Ngay_Sua], [ID_Dong], [ID_NV]) VALUES (4, 1, 1, 1, 1, 4, 12, 1, 0, CAST(N'2024-03-20' AS Date), CAST(N'2024-03-22' AS Date), 1, 2)
INSERT [dbo].[SANPHAM] ([ID_San_Pham], [ID_Chip], [ID_Pin], [ID_Man_Hinh], [ID_Cong_Sac], [Ho_Tro_Mang], [The_He], [So_Luong_Sim], [Trang_Thai], [Ngay_Tao], [Ngay_Sua], [ID_Dong], [ID_NV]) VALUES (6, 1, 1, 1, 1, 4, 12, 2, 0, CAST(N'2024-03-22' AS Date), NULL, 1, 2)
INSERT [dbo].[SANPHAM] ([ID_San_Pham], [ID_Chip], [ID_Pin], [ID_Man_Hinh], [ID_Cong_Sac], [Ho_Tro_Mang], [The_He], [So_Luong_Sim], [Trang_Thai], [Ngay_Tao], [Ngay_Sua], [ID_Dong], [ID_NV]) VALUES (7, 1, 1, 1, 1, 4, 12, 2, 0, CAST(N'2024-03-22' AS Date), NULL, 1, 2)
INSERT [dbo].[SANPHAM] ([ID_San_Pham], [ID_Chip], [ID_Pin], [ID_Man_Hinh], [ID_Cong_Sac], [Ho_Tro_Mang], [The_He], [So_Luong_Sim], [Trang_Thai], [Ngay_Tao], [Ngay_Sua], [ID_Dong], [ID_NV]) VALUES (8, 1, 1, 4, 1, 5, 15, 2, 0, CAST(N'2024-03-22' AS Date), NULL, 3, 2)
INSERT [dbo].[SANPHAM] ([ID_San_Pham], [ID_Chip], [ID_Pin], [ID_Man_Hinh], [ID_Cong_Sac], [Ho_Tro_Mang], [The_He], [So_Luong_Sim], [Trang_Thai], [Ngay_Tao], [Ngay_Sua], [ID_Dong], [ID_NV]) VALUES (9, 1, 1, 1, 1, 4, 12, 2, 0, CAST(N'2024-03-22' AS Date), NULL, 1, 2)
INSERT [dbo].[SANPHAM] ([ID_San_Pham], [ID_Chip], [ID_Pin], [ID_Man_Hinh], [ID_Cong_Sac], [Ho_Tro_Mang], [The_He], [So_Luong_Sim], [Trang_Thai], [Ngay_Tao], [Ngay_Sua], [ID_Dong], [ID_NV]) VALUES (10, 1, 1, 4, 1, 5, 15, 2, 1, CAST(N'2024-03-22' AS Date), NULL, 3, 2)
INSERT [dbo].[SANPHAM] ([ID_San_Pham], [ID_Chip], [ID_Pin], [ID_Man_Hinh], [ID_Cong_Sac], [Ho_Tro_Mang], [The_He], [So_Luong_Sim], [Trang_Thai], [Ngay_Tao], [Ngay_Sua], [ID_Dong], [ID_NV]) VALUES (11, 1, 1, 1, 1, 4, 14, 1, 0, CAST(N'2024-03-23' AS Date), NULL, 1, 2)
INSERT [dbo].[SANPHAM] ([ID_San_Pham], [ID_Chip], [ID_Pin], [ID_Man_Hinh], [ID_Cong_Sac], [Ho_Tro_Mang], [The_He], [So_Luong_Sim], [Trang_Thai], [Ngay_Tao], [Ngay_Sua], [ID_Dong], [ID_NV]) VALUES (12, 1, 1, 1, 1, 4, 14, 2, 0, CAST(N'2024-03-23' AS Date), CAST(N'2024-03-23' AS Date), 3, 2)
INSERT [dbo].[SANPHAM] ([ID_San_Pham], [ID_Chip], [ID_Pin], [ID_Man_Hinh], [ID_Cong_Sac], [Ho_Tro_Mang], [The_He], [So_Luong_Sim], [Trang_Thai], [Ngay_Tao], [Ngay_Sua], [ID_Dong], [ID_NV]) VALUES (13, 1, 1, 4, 1, 5, 15, 1, 0, CAST(N'2024-04-13' AS Date), CAST(N'2024-04-13' AS Date), 2, 2)
SET IDENTITY_INSERT [dbo].[SANPHAM] OFF
GO
SET IDENTITY_INSERT [dbo].[SANPHAMCHITIET] ON 

INSERT [dbo].[SANPHAMCHITIET] ([ID_SPCT], [ID_San_Pham], [ID_Ram], [ID_Rom], [ID_Mau], [ID_Model_Number], [Version_HDH], [Anh], [Tinh_Trang], [Phien_Ban], [Gia_Nhap], [Don_Gia], [Trang_Thai], [Ngay_Tao], [Ngay_Sua], [ID_NV], [mo_Ta]) VALUES (2, 8, 1, 1, 1, 1, 16, N'iphone-15-pro-white-titanium-pure-back-iphone-15-pro-white-titanium-pure-front-2up-screen-usen_2.jpg', 99, 1, 17000000, 22000000, 0, CAST(N'2024-03-15' AS Date), CAST(N'2024-04-13' AS Date), 2, NULL)
INSERT [dbo].[SANPHAMCHITIET] ([ID_SPCT], [ID_San_Pham], [ID_Ram], [ID_Rom], [ID_Mau], [ID_Model_Number], [Version_HDH], [Anh], [Tinh_Trang], [Phien_Ban], [Gia_Nhap], [Don_Gia], [Trang_Thai], [Ngay_Tao], [Ngay_Sua], [ID_NV], [mo_Ta]) VALUES (4, 9, 2, 1, 2, 2, 14, N'iphone-13-vihre.jpg', 100, 0, 100, 100, 0, CAST(N'2024-03-15' AS Date), CAST(N'2024-03-23' AS Date), 2, NULL)
INSERT [dbo].[SANPHAMCHITIET] ([ID_SPCT], [ID_San_Pham], [ID_Ram], [ID_Rom], [ID_Mau], [ID_Model_Number], [Version_HDH], [Anh], [Tinh_Trang], [Phien_Ban], [Gia_Nhap], [Don_Gia], [Trang_Thai], [Ngay_Tao], [Ngay_Sua], [ID_NV], [mo_Ta]) VALUES (6, 9, 1, 1, 2, 1, 16, N'1624874006-iPhone_12_Repair_Brisbane.jpg', 99, 1, 10000000, 22000000, 0, CAST(N'2024-03-21' AS Date), CAST(N'2024-04-13' AS Date), 2, NULL)
INSERT [dbo].[SANPHAMCHITIET] ([ID_SPCT], [ID_San_Pham], [ID_Ram], [ID_Rom], [ID_Mau], [ID_Model_Number], [Version_HDH], [Anh], [Tinh_Trang], [Phien_Ban], [Gia_Nhap], [Don_Gia], [Trang_Thai], [Ngay_Tao], [Ngay_Sua], [ID_NV], [mo_Ta]) VALUES (7, 12, 3, 2, 2, 2, 16, N'iphone-15-pro-white-titanium-pure-back-iphone-15-pro-white-titanium-pure-front-2up-screen-usen_2.jpg', 99, 1, 100, 10000000, 0, CAST(N'2024-03-23' AS Date), CAST(N'2024-03-25' AS Date), 2, NULL)
INSERT [dbo].[SANPHAMCHITIET] ([ID_SPCT], [ID_San_Pham], [ID_Ram], [ID_Rom], [ID_Mau], [ID_Model_Number], [Version_HDH], [Anh], [Tinh_Trang], [Phien_Ban], [Gia_Nhap], [Don_Gia], [Trang_Thai], [Ngay_Tao], [Ngay_Sua], [ID_NV], [mo_Ta]) VALUES (8, 9, 1, 1, 1, 1, 16, N'433531751_940482501009940_7225910841340153649_n.jpg', 99, 1, 15000000, 21000000, 0, CAST(N'2024-03-23' AS Date), CAST(N'2024-04-13' AS Date), 2, NULL)
INSERT [dbo].[SANPHAMCHITIET] ([ID_SPCT], [ID_San_Pham], [ID_Ram], [ID_Rom], [ID_Mau], [ID_Model_Number], [Version_HDH], [Anh], [Tinh_Trang], [Phien_Ban], [Gia_Nhap], [Don_Gia], [Trang_Thai], [Ngay_Tao], [Ngay_Sua], [ID_NV], [mo_Ta]) VALUES (9, 9, 2, 1, 2, 2, 14, N'iphone-13-vihre.jpg', 100, 0, 12000000, 20000000, 0, CAST(N'2024-03-23' AS Date), CAST(N'2024-04-13' AS Date), 2, NULL)
INSERT [dbo].[SANPHAMCHITIET] ([ID_SPCT], [ID_San_Pham], [ID_Ram], [ID_Rom], [ID_Mau], [ID_Model_Number], [Version_HDH], [Anh], [Tinh_Trang], [Phien_Ban], [Gia_Nhap], [Don_Gia], [Trang_Thai], [Ngay_Tao], [Ngay_Sua], [ID_NV], [mo_Ta]) VALUES (10, 13, 4, 4, 1, 2, 16, N'anh1.png', 100, 0, 17000000, 25000000, 0, CAST(N'2024-04-13' AS Date), CAST(N'2024-04-13' AS Date), 2, NULL)
SET IDENTITY_INSERT [dbo].[SANPHAMCHITIET] OFF
GO
SET IDENTITY_INSERT [dbo].[THONGSOMANHINH] ON 

INSERT [dbo].[THONGSOMANHINH] ([ID_Thong_So_Man_Hinh], [Loai_Man_Hinh], [Do_Phan_Giai], [Tan_So_Quet], [Trang_Thai], [Ngay_Sua], [Ngay_Tao]) VALUES (1, 1, 0, 60, 0, NULL, CAST(N'2024-03-20' AS Date))
INSERT [dbo].[THONGSOMANHINH] ([ID_Thong_So_Man_Hinh], [Loai_Man_Hinh], [Do_Phan_Giai], [Tan_So_Quet], [Trang_Thai], [Ngay_Sua], [Ngay_Tao]) VALUES (2, 2, 0, -1, 0, NULL, CAST(N'2024-03-22' AS Date))
INSERT [dbo].[THONGSOMANHINH] ([ID_Thong_So_Man_Hinh], [Loai_Man_Hinh], [Do_Phan_Giai], [Tan_So_Quet], [Trang_Thai], [Ngay_Sua], [Ngay_Tao]) VALUES (3, 2, 3, 120, 0, NULL, CAST(N'2024-03-22' AS Date))
INSERT [dbo].[THONGSOMANHINH] ([ID_Thong_So_Man_Hinh], [Loai_Man_Hinh], [Do_Phan_Giai], [Tan_So_Quet], [Trang_Thai], [Ngay_Sua], [Ngay_Tao]) VALUES (4, 1, 3, 120, 1, NULL, CAST(N'2024-03-22' AS Date))
SET IDENTITY_INSERT [dbo].[THONGSOMANHINH] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__IMEI__8DF371FD1AD6BBD1]    Script Date: 4/20/2024 10:30:10 AM ******/
ALTER TABLE [dbo].[IMEI] ADD UNIQUE NONCLUSTERED 
(
	[IMEI] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__KHACHHAN__CA1930A5220C0326]    Script Date: 4/20/2024 10:30:10 AM ******/
ALTER TABLE [dbo].[KHACHHANG] ADD UNIQUE NONCLUSTERED 
(
	[SDT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__KHUYENMA__C6A93E4EC852EB0E]    Script Date: 4/20/2024 10:30:10 AM ******/
ALTER TABLE [dbo].[KHUYENMAICT] ADD UNIQUE NONCLUSTERED 
(
	[Ma_Voucher] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__NHANVIEN__A955A0AA259F4223]    Script Date: 4/20/2024 10:30:10 AM ******/
ALTER TABLE [dbo].[NHANVIEN] ADD UNIQUE NONCLUSTERED 
(
	[CCCD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__NHANVIEN__CA1930A5678CAA46]    Script Date: 4/20/2024 10:30:10 AM ******/
ALTER TABLE [dbo].[NHANVIEN] ADD UNIQUE NONCLUSTERED 
(
	[SDT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[CAMERA]  WITH CHECK ADD FOREIGN KEY([ID_Camera])
REFERENCES [dbo].[CAMERAALL] ([ID_Camera])
GO
ALTER TABLE [dbo].[CAMERA]  WITH CHECK ADD FOREIGN KEY([ID_San_Pham])
REFERENCES [dbo].[SANPHAM] ([ID_San_Pham])
GO
ALTER TABLE [dbo].[GIOHANGCT]  WITH CHECK ADD FOREIGN KEY([ID_HD])
REFERENCES [dbo].[HOADON] ([ID_HD])
GO
ALTER TABLE [dbo].[GIOHANGCT]  WITH CHECK ADD FOREIGN KEY([IMEI])
REFERENCES [dbo].[IMEI] ([IMEI])
GO
ALTER TABLE [dbo].[HOADON]  WITH CHECK ADD FOREIGN KEY([ID_KH])
REFERENCES [dbo].[KHACHHANG] ([ID_KH])
GO
ALTER TABLE [dbo].[HOADON]  WITH CHECK ADD FOREIGN KEY([ID_KMCT])
REFERENCES [dbo].[KHUYENMAICT] ([ID_KMCT])
GO
ALTER TABLE [dbo].[HOADON]  WITH CHECK ADD FOREIGN KEY([ID_NV])
REFERENCES [dbo].[NHANVIEN] ([ID_NV])
GO
ALTER TABLE [dbo].[IMEI]  WITH CHECK ADD FOREIGN KEY([ID_NV])
REFERENCES [dbo].[NHANVIEN] ([ID_NV])
GO
ALTER TABLE [dbo].[IMEI]  WITH CHECK ADD FOREIGN KEY([ID_SPCT])
REFERENCES [dbo].[SANPHAMCHITIET] ([ID_SPCT])
GO
ALTER TABLE [dbo].[KHACHHANG]  WITH CHECK ADD FOREIGN KEY([ID_NV])
REFERENCES [dbo].[NHANVIEN] ([ID_NV])
GO
ALTER TABLE [dbo].[KHUYENMAI]  WITH CHECK ADD FOREIGN KEY([ID_NV])
REFERENCES [dbo].[NHANVIEN] ([ID_NV])
GO
ALTER TABLE [dbo].[KHUYENMAICT]  WITH CHECK ADD FOREIGN KEY([ID_KM])
REFERENCES [dbo].[KHUYENMAI] ([ID_KM])
GO
ALTER TABLE [dbo].[MANHINH]  WITH CHECK ADD FOREIGN KEY([ID_Thong_So_Man_Hinh])
REFERENCES [dbo].[THONGSOMANHINH] ([ID_Thong_So_Man_Hinh])
GO
ALTER TABLE [dbo].[SANPHAM]  WITH CHECK ADD FOREIGN KEY([ID_Chip])
REFERENCES [dbo].[CHIP] ([ID_Chip])
GO
ALTER TABLE [dbo].[SANPHAM]  WITH CHECK ADD FOREIGN KEY([ID_Cong_Sac])
REFERENCES [dbo].[CONGSAC] ([ID_Cong_Sac])
GO
ALTER TABLE [dbo].[SANPHAM]  WITH CHECK ADD FOREIGN KEY([ID_Dong])
REFERENCES [dbo].[DONG] ([ID_Dong])
GO
ALTER TABLE [dbo].[SANPHAM]  WITH CHECK ADD FOREIGN KEY([ID_Man_Hinh])
REFERENCES [dbo].[MANHINH] ([ID_Man_Hinh])
GO
ALTER TABLE [dbo].[SANPHAM]  WITH CHECK ADD FOREIGN KEY([ID_NV])
REFERENCES [dbo].[NHANVIEN] ([ID_NV])
GO
ALTER TABLE [dbo].[SANPHAM]  WITH CHECK ADD FOREIGN KEY([ID_Pin])
REFERENCES [dbo].[PIN] ([ID_Pin])
GO
ALTER TABLE [dbo].[SANPHAMCHITIET]  WITH CHECK ADD FOREIGN KEY([ID_Mau])
REFERENCES [dbo].[MAUSAC] ([ID_Mau])
GO
ALTER TABLE [dbo].[SANPHAMCHITIET]  WITH CHECK ADD FOREIGN KEY([ID_Model_Number])
REFERENCES [dbo].[MODELNUMBER] ([ID_Model_Number])
GO
ALTER TABLE [dbo].[SANPHAMCHITIET]  WITH CHECK ADD FOREIGN KEY([ID_NV])
REFERENCES [dbo].[NHANVIEN] ([ID_NV])
GO
ALTER TABLE [dbo].[SANPHAMCHITIET]  WITH CHECK ADD FOREIGN KEY([ID_Ram])
REFERENCES [dbo].[RAM] ([ID_Ram])
GO
ALTER TABLE [dbo].[SANPHAMCHITIET]  WITH CHECK ADD FOREIGN KEY([ID_Rom])
REFERENCES [dbo].[ROM] ([ID_Rom])
GO
ALTER TABLE [dbo].[SANPHAMCHITIET]  WITH CHECK ADD FOREIGN KEY([ID_San_Pham])
REFERENCES [dbo].[SANPHAM] ([ID_San_Pham])
GO
ALTER TABLE [dbo].[KHUYENMAI]  WITH CHECK ADD  CONSTRAINT [CHK_1] CHECK  (([ngay_Ket_Thuc]>=[ngay_Bat_Dau]))
GO
ALTER TABLE [dbo].[KHUYENMAI] CHECK CONSTRAINT [CHK_1]
GO
USE [master]
GO
ALTER DATABASE [DA1] SET  READ_WRITE 
GO
