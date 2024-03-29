USE [DB_FOC90]
GO
/****** Object:  Table [dbo].[Lotes_de_produccion]    Script Date: 02/26/2010 10:52:10 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING OFF
GO
CREATE TABLE [dbo].[Lotes_de_produccion](
	[sucursal] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL,
	[Codigo_Pais] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL,
	[numero_lote] [int] IDENTITY(1,1) NOT NULL,
	[sucursal_lote] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NULL,
	[id_lote] [varchar](50) COLLATE SQL_Latin1_General_CP850_CI_AS NULL,
	[fecha_lote] [datetime] NULL,
	[fecha_vencimiento] [datetime] NULL,
	[producto] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NULL,
	[estado] [varchar](20) COLLATE SQL_Latin1_General_CP850_CI_AS NULL,
	[cant_env_llenados] [int] NULL,
	[cant_env_revisados] [int] NULL,
	[cant_env_entregados] [int] NULL,
	[cant_env_identif_llenados] [int] NULL,
	[cant_env_identif_revisados] [int] NULL,
	[identif_asociados_sn] [char](1) COLLATE SQL_Latin1_General_CP850_CI_AS NULL,
	[cant_impresiones_de_etiquetas] [int] NULL,
	[cant_impresiones_certificado] [int] NULL,
	[sucursal_operador] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NULL,
	[responsable_llenado] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NULL,
	[sucursal_que_envio_el_lote] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NULL,
	[observaciones] [varchar](150) COLLATE SQL_Latin1_General_CP850_CI_AS NULL,
	[fecha_modif_alta_reg] [datetime] NULL,
	[rowid] [timestamp] NULL,
	[numero_lote_old] [varchar](20) COLLATE SQL_Latin1_General_CP850_CI_AS NULL,
	[volumen_cargado] [decimal](20, 4) NULL,
	[cant_env_ident_del_lote] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[numero_lote] ASC,
	[sucursal] ASC
)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Statistic [_dta_stat_792090308_1_3_5_8]    Script Date: 02/26/2010 10:52:11 ******/
CREATE STATISTICS [_dta_stat_792090308_1_3_5_8] ON [dbo].[Lotes_de_produccion]([sucursal], [numero_lote], [id_lote], [producto])
GO
/****** Object:  Statistic [_dta_stat_792090308_8_1_3]    Script Date: 02/26/2010 10:52:11 ******/
CREATE STATISTICS [_dta_stat_792090308_8_1_3] ON [dbo].[Lotes_de_produccion]([producto], [sucursal], [numero_lote])
GO
/****** Object:  Statistic [_dta_stat_792090308_8_5_1]    Script Date: 02/26/2010 10:52:11 ******/
CREATE STATISTICS [_dta_stat_792090308_8_5_1] ON [dbo].[Lotes_de_produccion]([producto], [id_lote], [sucursal])
GO
USE [DB_FOC90]
GO
ALTER TABLE [dbo].[Lotes_de_produccion]  WITH CHECK ADD FOREIGN KEY([Codigo_Pais])
REFERENCES [dbo].[Ref_Pais] ([codigo_pais])
GO
ALTER TABLE [dbo].[Lotes_de_produccion]  WITH CHECK ADD FOREIGN KEY([sucursal])
REFERENCES [dbo].[Sucursal] ([sucursal])
GO
ALTER TABLE [dbo].[Lotes_de_produccion]  WITH CHECK ADD FOREIGN KEY([responsable_llenado], [sucursal_operador])
REFERENCES [dbo].[Operador] ([operador], [sucursal])