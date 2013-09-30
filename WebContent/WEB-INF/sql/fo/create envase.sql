USE [DB_FOC90]
GO
/****** Object:  Table [dbo].[Envase]    Script Date: 02/26/2010 10:54:13 ******/
SET ANSI_NULLS OFF
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING OFF
GO
CREATE TABLE [dbo].[Envase](
	[envase] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL,
	[Forma_de_entrega] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NULL,
	[Codigo_tipo_envase] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL,
	[unidad_medida] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL,
	[agrupamiento_envases] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL,
	[descripcion_envase] [varchar](50) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL,
	[capacidad_liquida] [decimal](20, 4) NOT NULL,
	[capacidad_gaseosa] [decimal](20, 4) NOT NULL,
	[equivalencia_cilindros] [decimal](20, 4) NOT NULL,
	[peso_lleno] [decimal](20, 4) NOT NULL,
	[peso_vacio] [decimal](20, 4) NOT NULL,
	[capacidad_unidades] [int] NOT NULL,
	[valor_envase_dolares] [decimal](25, 12) NOT NULL,
	[Presion_trabajo] [decimal](20, 4) NULL,
	[Fecha_modif_alta_reg] [datetime] NOT NULL,
	[rowid] [timestamp] NOT NULL,
	[es_cilindro_sn] [char](1) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL,
	[Fecha_alta] [datetime] NOT NULL
) ON [PRIMARY]
SET ANSI_PADDING ON
ALTER TABLE [dbo].[Envase] ADD [Particular_sn] [varchar](1) COLLATE SQL_Latin1_General_CP850_CI_AS NOT NULL DEFAULT ('N')
SET ANSI_PADDING OFF
ALTER TABLE [dbo].[Envase] ADD [clasificacion_fiscal] [varchar](10) COLLATE SQL_Latin1_General_CP850_CI_AS NULL
ALTER TABLE [dbo].[Envase] ADD [equivalencia_real_cilindros] [decimal](20, 4) NULL
/****** Object:  Index [PK__Envase__7BDDD45E]    Script Date: 02/26/2010 10:54:13 ******/
ALTER TABLE [dbo].[Envase] ADD PRIMARY KEY CLUSTERED 
(
	[envase] ASC
)WITH (SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
USE [DB_FOC90]
GO
ALTER TABLE [dbo].[Envase]  WITH CHECK ADD FOREIGN KEY([agrupamiento_envases])
REFERENCES [dbo].[Ref_Agrup_envases] ([agrupamiento_envases])
GO
ALTER TABLE [dbo].[Envase]  WITH CHECK ADD FOREIGN KEY([clasificacion_fiscal])
REFERENCES [dbo].[Ref_clasificacion_fiscal] ([Clasificacion_fiscal])
GO
ALTER TABLE [dbo].[Envase]  WITH CHECK ADD FOREIGN KEY([Codigo_tipo_envase])
REFERENCES [dbo].[Tipos_de_envase] ([Codigo_tipo_envase])
GO
ALTER TABLE [dbo].[Envase]  WITH CHECK ADD FOREIGN KEY([Forma_de_entrega])
REFERENCES [dbo].[Ref_formas_entrega] ([Forma_de_entrega])
GO
ALTER TABLE [dbo].[Envase]  WITH CHECK ADD FOREIGN KEY([unidad_medida])
REFERENCES [dbo].[Ref_Unidades_medida] ([unidad_medida])