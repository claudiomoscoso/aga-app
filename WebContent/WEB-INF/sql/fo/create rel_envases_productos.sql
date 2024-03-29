USE [DB_FOC90]
GO
/****** Object:  Table [dbo].[Rel_envases_productos]    Script Date: 02/26/2010 10:54:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING OFF
GO
CREATE TABLE [dbo].[Rel_envases_productos](
	[producto] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL,
	[envase] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL,
	[agrupador_stock_en_cliente] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NULL,
	[unidad_medida_presion] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NULL,
	[Acepta_particular_SN] [char](1) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL DEFAULT ('N'),
	[Cap_unid_producto] [decimal](20, 4) NULL,
	[agrupador_de_facturas] [varchar](20) COLLATE SQL_Latin1_General_CP850_CI_AS NULL,
	[Max_desvio_volumen_positivo] [decimal](20, 4) NOT NULL,
	[Max_desvio_volumen_negativo] [decimal](20, 4) NOT NULL
) ON [PRIMARY]
SET ANSI_PADDING ON
ALTER TABLE [dbo].[Rel_envases_productos] ADD [cuenta_estadistica_1] [varchar](30) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL
SET ANSI_PADDING OFF
ALTER TABLE [dbo].[Rel_envases_productos] ADD [unidad_estadistica_1] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL
ALTER TABLE [dbo].[Rel_envases_productos] ADD [Coeficiente_conversion_1] [decimal](7, 4) NOT NULL
ALTER TABLE [dbo].[Rel_envases_productos] ADD [cuenta_estadistica_2] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL
ALTER TABLE [dbo].[Rel_envases_productos] ADD [unidad_estadistica_2] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL
ALTER TABLE [dbo].[Rel_envases_productos] ADD [Coeficiente_conversion_2] [decimal](7, 4) NOT NULL
ALTER TABLE [dbo].[Rel_envases_productos] ADD [cuenta_estadistica_3] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL
ALTER TABLE [dbo].[Rel_envases_productos] ADD [unidad_estadistica_3] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL
ALTER TABLE [dbo].[Rel_envases_productos] ADD [Coeficiente_conversion_3] [decimal](7, 4) NOT NULL
ALTER TABLE [dbo].[Rel_envases_productos] ADD [cuenta_estadistica_4] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL
ALTER TABLE [dbo].[Rel_envases_productos] ADD [unidad_estadistica_4] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL
ALTER TABLE [dbo].[Rel_envases_productos] ADD [Coeficiente_conversion_4] [decimal](7, 4) NOT NULL
ALTER TABLE [dbo].[Rel_envases_productos] ADD [cuenta_estadistica_5] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL
ALTER TABLE [dbo].[Rel_envases_productos] ADD [unidad_estadistica_5] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL
ALTER TABLE [dbo].[Rel_envases_productos] ADD [Coeficiente_conversion_5] [decimal](7, 4) NOT NULL
ALTER TABLE [dbo].[Rel_envases_productos] ADD [Valorizacion] [decimal](20, 4) NOT NULL
ALTER TABLE [dbo].[Rel_envases_productos] ADD [Agrupador_cce] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL
ALTER TABLE [dbo].[Rel_envases_productos] ADD [DP2_sn] [varchar](1) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL DEFAULT ('N')
ALTER TABLE [dbo].[Rel_envases_productos] ADD [Fecha_alta] [datetime] NOT NULL
ALTER TABLE [dbo].[Rel_envases_productos] ADD [Acepta_otras_marcas_sn] [char](1) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL DEFAULT ('N')
ALTER TABLE [dbo].[Rel_envases_productos] ADD [codigo_env_prod_ft] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NULL
ALTER TABLE [dbo].[Rel_envases_productos] ADD [Fecha_modif_alta_reg] [datetime] NOT NULL
ALTER TABLE [dbo].[Rel_envases_productos] ADD [rowid] [timestamp] NOT NULL
/****** Object:  Index [PK__Rel_envases_prod__6F0DE925]    Script Date: 02/26/2010 10:54:44 ******/
ALTER TABLE [dbo].[Rel_envases_productos] ADD PRIMARY KEY CLUSTERED 
(
	[producto] ASC,
	[envase] ASC
)WITH (SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
USE [DB_FOC90]
GO
ALTER TABLE [dbo].[Rel_envases_productos]  WITH CHECK ADD FOREIGN KEY([Agrupador_cce])
REFERENCES [dbo].[Ref_agrupador_cce] ([Agrupador_cce])
GO
ALTER TABLE [dbo].[Rel_envases_productos]  WITH CHECK ADD FOREIGN KEY([agrupador_stock_en_cliente])
REFERENCES [dbo].[Agrupador_para_Stock_en_Client] ([agrupador_stock_en_cliente])
GO
ALTER TABLE [dbo].[Rel_envases_productos]  WITH CHECK ADD FOREIGN KEY([cuenta_estadistica_1])
REFERENCES [dbo].[Ref_cuenta_estadistica_1] ([cuenta_estadistica_1])
GO
ALTER TABLE [dbo].[Rel_envases_productos]  WITH CHECK ADD FOREIGN KEY([cuenta_estadistica_5])
REFERENCES [dbo].[Ref_cuenta_estadistica_5] ([cuenta_estadistica_5])
GO
ALTER TABLE [dbo].[Rel_envases_productos]  WITH CHECK ADD FOREIGN KEY([cuenta_estadistica_4])
REFERENCES [dbo].[Ref_cuenta_estadistica_4] ([cuenta_estadistica_4])
GO
ALTER TABLE [dbo].[Rel_envases_productos]  WITH CHECK ADD FOREIGN KEY([cuenta_estadistica_3])
REFERENCES [dbo].[Ref_cuenta_estadistica_3] ([cuenta_estadistica_3])
GO
ALTER TABLE [dbo].[Rel_envases_productos]  WITH CHECK ADD FOREIGN KEY([cuenta_estadistica_2])
REFERENCES [dbo].[Ref_cuenta_estadistica_2] ([cuenta_estadistica_2])
GO
ALTER TABLE [dbo].[Rel_envases_productos]  WITH CHECK ADD FOREIGN KEY([envase])
REFERENCES [dbo].[Envase] ([envase])
GO
ALTER TABLE [dbo].[Rel_envases_productos]  WITH CHECK ADD  CONSTRAINT [FK__Rel_envas__produ__309193BF] FOREIGN KEY([producto])
REFERENCES [dbo].[Producto] ([producto])
GO
ALTER TABLE [dbo].[Rel_envases_productos]  WITH CHECK ADD FOREIGN KEY([unidad_estadistica_5])
REFERENCES [dbo].[Ref_Unidades_medida] ([unidad_medida])
GO
ALTER TABLE [dbo].[Rel_envases_productos]  WITH CHECK ADD FOREIGN KEY([unidad_estadistica_4])
REFERENCES [dbo].[Ref_Unidades_medida] ([unidad_medida])
GO
ALTER TABLE [dbo].[Rel_envases_productos]  WITH CHECK ADD FOREIGN KEY([unidad_estadistica_3])
REFERENCES [dbo].[Ref_Unidades_medida] ([unidad_medida])
GO
ALTER TABLE [dbo].[Rel_envases_productos]  WITH CHECK ADD FOREIGN KEY([unidad_estadistica_2])
REFERENCES [dbo].[Ref_Unidades_medida] ([unidad_medida])
GO
ALTER TABLE [dbo].[Rel_envases_productos]  WITH CHECK ADD FOREIGN KEY([unidad_estadistica_1])
REFERENCES [dbo].[Ref_Unidades_medida] ([unidad_medida])
GO
ALTER TABLE [dbo].[Rel_envases_productos]  WITH CHECK ADD FOREIGN KEY([unidad_medida_presion])
REFERENCES [dbo].[Ref_Unidades_medida] ([unidad_medida])